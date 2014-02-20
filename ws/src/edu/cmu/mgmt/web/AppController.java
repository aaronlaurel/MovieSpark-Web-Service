package edu.cmu.mgmt.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.cmu.mgmt.common.TopicType;
import edu.cmu.mgmt.model.Picture;
import edu.cmu.mgmt.model.Topic;
import edu.cmu.mgmt.model.flickr.FlickrLocation.Photo.Location;
import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;
import edu.cmu.mgmt.model.flickr.FlickrPhotos;
import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.twitter.TwitterUser;
import edu.cmu.mgmt.model.vo.CategoryVo;
import edu.cmu.mgmt.model.vo.PictureVo;
import edu.cmu.mgmt.model.vo.TopicVo;
import edu.cmu.mgmt.service.FlickrService;
import edu.cmu.mgmt.service.TwitterService;
import edu.cmu.mgmt.service.UserService;
import edu.cmu.mgmt.service.impl.FlickrServiceImpl;
import edu.cmu.mgmt.service.impl.TwitterServiceImpl;
import edu.cmu.mgmt.utils.oauth.ServiceConnect;

@Controller
public class AppController {

	@Autowired
	private UserService userService;

	private OAuthService twitterOAuthService, flickrOAuthService;
	private Token twitterRequestToken, flickrRequestToken;

	@RequestMapping("/welcome.do")
	public void auth(HttpServletResponse response) throws IOException {
		twitterOAuthService = ServiceConnect.connectToTwitter();
		flickrOAuthService = ServiceConnect.connectToFlicker();

		// Get the request token
		twitterRequestToken = twitterOAuthService.getRequestToken();
		flickrRequestToken = flickrOAuthService.getRequestToken();

		// Making the user validate twitter request token
		response.sendRedirect(twitterOAuthService
				.getAuthorizationUrl(twitterRequestToken));
	}

	@RequestMapping("/redirect_flickr.do")
	public void redirectFlickr(String oauth_verifier,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		// get twitter accessToken
		Token accessToken = twitterOAuthService.getAccessToken(
				twitterRequestToken, new Verifier(oauth_verifier));
		session.setAttribute("twitterAccessToken", accessToken);

		// Making the user validate flickr request token
		response.sendRedirect(flickrOAuthService
				.getAuthorizationUrl(flickrRequestToken) + "&perms=delete");
	}

	@RequestMapping("/auth_flickr.do")
	public void auth_Flickr(String oauth_verifier,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		// get flickr accessToken
		Token accessToken = flickrOAuthService.getAccessToken(
				flickrRequestToken, new Verifier(oauth_verifier));
		session.setAttribute("flickrAccessToken", accessToken);
		response.sendRedirect("init.do");
	}

	@RequestMapping("/init.do")
	public String init(HttpServletRequest request, HttpSession session) {
		Token accessToken = (Token) session.getAttribute("twitterAccessToken");
		TwitterService twitterService = new TwitterServiceImpl();
		TwitterUser user = twitterService.getTwitterUser(accessToken,
				twitterOAuthService);
		session.setAttribute("user", user);

		List<Topic> topiclist = userService.getHotestTopics();
		for (int i = topiclist.size() - 1; i >= 8; i--) {
			topiclist.remove(i);
		}
		request.setAttribute("topiclist", topiclist);
		return "main";
	}

	@RequestMapping("/view_topics_link.do")
	public String viewTopicsLink(HttpServletRequest request) {
		List<Topic> hotesttopic = userService.getHotestTopics();
		List<Topic> latesttopic = userService.getLatestTopics();
		for (int i = hotesttopic.size() - 1; i >= 3; i--) {
			hotesttopic.remove(i);
		}
		for (int i = latesttopic.size() - 1; i >= 3; i--) {
			latesttopic.remove(i);
		}
		request.setAttribute("hotesttopic", hotesttopic);
		request.setAttribute("latesttopic", latesttopic);
		return "view_topics";
	}

	@RequestMapping("/topic_detail_link.do")
	public String topicDetailLink(Integer topic_id, HttpServletRequest request) {
		Topic topic = userService.getTopicById(topic_id);
		request.setAttribute("topic", topic);

		List<Picture> picturelist = userService.getPictures(topic_id);
		request.setAttribute("picturelist", picturelist);

		return "topic_detail";
	}

	@RequestMapping("/photo_detail_link.do")
	public String createPhotoDetialJSP(Integer pid, HttpServletRequest request,
			HttpSession session) {
		Token accessToken = (Token) session.getAttribute("twitterAccessToken");
		Picture picture = userService.getPicture(pid);
		picture = userService.updateClick(pid);
		request.setAttribute("picture", picture);
		List<Oembed> oembeds = userService.getOemTweets(accessToken,
				twitterOAuthService, pid);
		request.setAttribute("oembeds", oembeds);
		return "photo_detail";
	}

	@RequestMapping("/comment.do")
	public String submitComment(Integer pid, String comment,
			HttpSession session, HttpServletRequest request) {
		Token accessToken = (Token) session.getAttribute("twitterAccessToken");
		Picture picture = userService.getPicture(pid);
		request.setAttribute("picture", picture);
		comment = comment + "   (From: MovieSpark.com)";
		List<Oembed> list = userService.submitComment(accessToken, pid,
				comment, twitterOAuthService);
		request.setAttribute("oembeds", list);
		return "photo_detail";
	}

	@RequestMapping("/create_topic_link.do")
	public String createTopicJSP() {
		return "create_topic";
	}

	@RequestMapping("/create_topic.do")
	public String createTopic(MultipartHttpServletRequest request,
			HttpSession session) throws IOException {
		// check valid movie name
		List<String> errors = new LinkedList<String>();
		request.setAttribute("errors", errors);
		if (request.getParameter("movie") == null || request.getParameter("movie").equals("") 
				|| request.getParameter("movie").equals("Movie not found!")) {
			errors.add("You should choose a valid movie name!");
			return "create_topic";
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		TwitterUser user = (TwitterUser) session.getAttribute("user");
		Token accessToken = (Token) session.getAttribute("flickrAccessToken");

		Topic topic = new Topic();
		topic.setUser_id(user.getId_str());
		topic.setTitle(request.getParameter("topic"));
		topic.setCreate_date(new Date());
		topic.setRank(0);
		TopicType topicType = null;
		for (TopicType type : TopicType.values()) {
			if (type.ordinal() == Integer.valueOf(request
					.getParameter("category"))) {
				topicType = type;
				break;
			}
		}
		topic.setTopicTpye(topicType);
		// photo part
		MultipartFile file = request.getFile("photo");
		parameters.put("title", request.getParameter("title"));
		parameters.put("photo", file.getInputStream());
		parameters.put("description", request.getParameter("description"));

		String movie = request.getParameter("movie");
		topic = userService.createTopic(topic, accessToken, flickrOAuthService,
				parameters, user.getId_str(), movie);
		request.setAttribute("topic", topic);

		List<Picture> picturelist = userService
				.getPictures(topic.getTopic_id());
		request.setAttribute("picturelist", picturelist);

		return "topic_detail";
	}

	@RequestMapping("/upload_photo_link.do")
	public String createUploadPhotoJSP(Integer topic_id,
			HttpServletRequest request) {
		Topic topic = userService.getTopicById(topic_id);
		request.setAttribute("topic", topic);
		return "upload_photo";
	}

	@RequestMapping("/upload_photo.do")
	public String uploadPhoto(MultipartHttpServletRequest request,
			HttpSession session) throws IOException {
		// check valid movie name
		List<String> errors = new LinkedList<String>();
		request.setAttribute("errors", errors);
		if (request.getParameter("movie") == null || request.getParameter("movie").equals("") 
				|| request.getParameter("movie").equals("Movie not found!")) {
			errors.add("You should choose a valid movie name!");
			return "upload_photo";
		}
		
		Token accessToken = (Token) session.getAttribute("flickrAccessToken");
		TwitterUser user = (TwitterUser) session.getAttribute("user");

		Map<String, Object> parameters = new HashMap<String, Object>();
		MultipartFile file = request.getFile("photo");
		parameters.put("title", request.getParameter("title"));
		parameters.put("photo", file.getInputStream());
		parameters.put("description", request.getParameter("description"));

		String movie = request.getParameter("movie");
		Picture picture = userService.createPhoto(accessToken,
				flickrOAuthService, parameters, user.getId_str(),
				Integer.parseInt(request.getParameter("topic_id")), movie);
		request.setAttribute("picture", picture);

		List<Oembed> oembeds = userService.getOemTweets(accessToken,
				twitterOAuthService, picture.getPicture_id());
		request.setAttribute("oembeds", oembeds);

		return "photo_detail";
	}

	@RequestMapping("/map.do")
	public String map(HttpSession session, HttpServletRequest request,
			Location location) {
		FlickrService flickrService = new FlickrServiceImpl();
		Token accessToken = (Token) session.getAttribute("flickrAccessToken");
		FlickrPhotos photos = flickrService.getPhotosForLocation(accessToken,
				flickrOAuthService, location.getLatitude(),
				location.getLongitude());
		for (Photo photo : photos.getPhotoList()) {
			photo.setPhotoLocation(flickrService
					.getPhotoLocation(accessToken, flickrOAuthService,
							photo.getId()).getPhoto().getLocation());
		}
		request.setAttribute("photos", photos.getPhotoList());
		return "map";
	}

	@RequestMapping("/topic_stat.do")
	public String topicStat(HttpServletRequest request) {
		List<Topic> list = userService.getTopics();
		List<TopicVo> topicStatList = new ArrayList<TopicVo>();
		for (int i = 0; i < list.size(); i++) {
			Topic t = list.get(i);
			int picStat = userService.getPicturesAmountByTopic(t.getTopic_id());
			int commStat = userService
					.getCommentsAmountByTopic(t.getTopic_id());
			TopicVo tv = new TopicVo();
			tv.setCommStat(commStat);
			tv.setPicStat(picStat);
			tv.setTitle(t.getTitle());
			tv.setRank(t.getRank());
			tv.setType(t.getTopicTpye());
			topicStatList.add(tv);
		}
		request.setAttribute("TopicList", topicStatList);
		return "topic_stat";
	}

	@RequestMapping("/pic_stat.do")
	public String picStat(HttpServletRequest request) {
		List<PictureVo> list = userService.getPictureVo();
		request.setAttribute("picList", list);
		return "pic_stat";
	}

	@RequestMapping("/category_stat.do")
	public String categoryStat(HttpServletRequest request) {
		List<CategoryVo> list = userService.getCategoryStat();
		request.setAttribute("cateList", list);
		return "cate_stat";
	}

	@RequestMapping("/topic_list_link.do")
	public String topicList(HttpServletRequest request) {
		List<Topic> topiclist = userService.getTopics();
		request.setAttribute("topiclist", topiclist);
		return "topic_list";
	}

	@RequestMapping("/cate_stat_redirect.do")
	public String catRedirect(HttpServletRequest request, TopicType cat) {
		List<Topic> topiclist = userService.getTopicsByCat(cat);
		request.setAttribute("topiclist", topiclist);
		return "topic_list";
	}

	@RequestMapping("/pic_stat_redirect.do")
	public String picRedirect(HttpServletRequest request, String title,
			HttpSession session) {
		Token accessToken = (Token) session.getAttribute("twitterAccessToken");
		Picture picture = userService.getPictureByTitle(title);
		request.setAttribute("picture", picture);
		List<Oembed> oembeds = userService.getOemTweets(accessToken,
				twitterOAuthService, picture.getPicture_id());
		request.setAttribute("oembeds", oembeds);
		return "photo_detail";
	}

	@RequestMapping("/topic_stat_redirect.do")
	public String topicRedirect(HttpServletRequest request, String title) {
		Topic topic = userService.getTopicByTitle(title);
		request.setAttribute("topic", topic);

		List<Picture> picturelist = userService
				.getPictures(topic.getTopic_id());
		request.setAttribute("picturelist", picturelist);

		return "topic_detail";
	}
}
