package edu.cmu.mgmt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmu.mgmt.common.TopicType;
import edu.cmu.mgmt.dao.BaseDao;
import edu.cmu.mgmt.model.Comment;
import edu.cmu.mgmt.model.Picture;
import edu.cmu.mgmt.model.Topic;
import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;
import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.twitter.Tweet;
import edu.cmu.mgmt.model.vo.CategoryVo;
import edu.cmu.mgmt.model.vo.PictureVo;
import edu.cmu.mgmt.service.FlickrService;
import edu.cmu.mgmt.service.TwitterService;
import edu.cmu.mgmt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDao<Picture> pictureDao;

	@Autowired
	private BaseDao<Topic> topicDao;

	@Autowired
	private BaseDao<Comment> commentDao;

	public List<Picture> getPictures(int topic_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", topic_id);
		return pictureDao.find("from Picture p where p.topic_id=:tid", params);
	}

	public List<Topic> getHotestTopics() {
		return topicDao.find("from Topic t order by t.rank desc");
	}

	public List<Topic> getLatestTopics() {
		return topicDao.find("from Topic t order by t.create_date desc");
	}

	public Topic createTopic(Topic topic, Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters,
			String userid, String movie) {
		// save topic
		this.saveOrUpdateTopic(topic);

		// create picture
		Picture pic = this.createPhoto(accessToken, flickrOAuthService,
				parameters, userid, topic.getTopic_id(), movie);
		topic.setUrl(pic.getUrl());
		// update topic
		this.saveOrUpdateTopic(topic);
		return topic;
	}

	public Picture createPhoto(Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters,
			String userId, Integer topicId, String movie) {
		FlickrService flickrService = new FlickrServiceImpl();
		String photoId = flickrService.uploadPhoto(accessToken,
				flickrOAuthService, parameters);
		Photo photo = flickrService.getPhotoInfo(accessToken,
				flickrOAuthService, photoId);

		Picture pic = new Picture();
		pic.setCreate_date(new Date());
		pic.setFlickr_pid(photoId);
		pic.setTitle((String) parameters.get("title"));
		pic.setDescription((String) parameters.get("description"));
		pic.setMovie(movie);
		pic.setUser_id(userId);
		pic.setTopic_id(topicId);
		pic.setUrl(photo.getUrl());
		pic.setClick_count(1);
		this.savePicture(pic);

		// update topic rank, upload + 5
		Topic topic = this.getTopicById(topicId);
		topic.setRank(topic.getRank() + 5);
		this.saveOrUpdateTopic(topic);

		return pic;
	}

	public List<Oembed> submitComment(Token accessToken, int pid,
			String content, OAuthService twitterOAuthService) {
		TwitterService twitterService = new TwitterServiceImpl();
		// generate Tweet comment;
		Tweet tweet = twitterService.createTweet(accessToken,
				twitterOAuthService, content);

		// generate the comment record
		Comment comment = new Comment();
		comment.setPhoto_id(pid);
		comment.setTweet_id(tweet.getId());
		this.saveComment(comment);

		// update topic rank, comment + 1
		Picture picture = this.getPicture(pid);
		Topic topic = this.getTopicById(picture.getTopic_id());
		topic.setRank(topic.getRank() + 1);
		this.saveOrUpdateTopic(topic);

		// get the comment list by pid
		List<Oembed> oemList = this.getOemTweets(accessToken,
				twitterOAuthService, pid);
		return oemList;
	}

	public List<Comment> getCommentListByPid(int pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		List<Comment> list = commentDao.find(
				"from Comment c where c.photo_id=:pid", params);
		return list;
	}

	public List<Oembed> getOemTweets(Token accessToken,
			OAuthService twitterOAuthService, int pid) {
		TwitterService twitterService = new TwitterServiceImpl();
		List<Oembed> list = new ArrayList<Oembed>();
		for (Comment comment : getCommentListByPid(pid)) {
			list.add(twitterService.getOemTweet(accessToken,
					twitterOAuthService, comment.getTweet_id()));
		}
		return list;
	}

	public void savePicture(Picture pic) {
		pictureDao.save(pic);
	}

	public Topic saveOrUpdateTopic(Topic topic) {
		topicDao.saveOrUpdate(topic);
		return topic;
	}

	public void saveComment(Comment comment) {
		commentDao.save(comment);
	}

	public Topic getTopicById(int tid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", tid);
		return topicDao.find("from Topic t where t.topic_id=:tid", params).get(
				0);
	}

	public Topic getTopicByTitle(String title) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		return topicDao.find("from Topic t where t.title =:title", params).get(
				0);
	}

	public Picture getPicture(int pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		return pictureDao
				.find("from Picture p where p.picture_id=:pid", params).get(0);
	}

	public Picture getPictureByFlickrId(String flickr_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fid", flickr_id);
		return pictureDao
				.find("from Picture p where p.flickr_pid=:fid", params).get(0);
	}

	public int getPicturesAmountByTopic(int topic_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", topic_id);
		long amount = pictureDao
				.count("select count(*) from Picture p where p.topic_id =:tid",
						params);
		return (int) amount;
	}

	public List<Picture> getPicturesByTopic(int topic_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", topic_id);
		return pictureDao.find("from Picture p where p.topic_id =:tid", params);
	}

	public List<Topic> getTopics() {
		return topicDao.find("from Topic");
	}

	public int getCommentsAmountByTopic(int topic_id) {
		List<Picture> list = getPicturesByTopic(topic_id);
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			Picture p = list.get(i);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", p.getPicture_id());
			long commentsAmount = commentDao.count(
					"select count(*) from Comment c where c.photo_id =:pid",
					params);
			count += commentsAmount;
		}
		return count;
	}

	public int getPictureComments(int pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		long amount = commentDao
				.count("select count(*) from Comment c where c.photo_id =:pid",
						params);
		return (int) amount;
	}

	public List<PictureVo> getPictureVo() {
		List<Picture> list = pictureDao.find("from Picture");
		List<PictureVo> vList = new ArrayList<PictureVo>();
		for (int i = 0; i < list.size(); i++) {
			Picture p = list.get(i);
			int comments = getPictureComments(p.getPicture_id());
			int visitCounts = p.getClick_count();
			double ratio = (double) comments / (double) visitCounts;
			PictureVo pv = new PictureVo();
			pv.setRatio(ratio);
			pv.setTitle(p.getTitle());
			vList.add(pv);
		}
		return vList;
	}

	public List<CategoryVo> getCategoryStat() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		for (TopicType type : TopicType.values()) {
			params.put("type", type);
			long amount = commentDao.count(
					"select count(*) from Topic t where t.topicTpye =:type",
					params);
			CategoryVo vo = new CategoryVo();
			vo.setAmounts((int) amount);
			vo.setType(type);
			list.add(vo);
		}
		return list;
	}

	public Picture updateClick(int pid) {
		Picture pic = this.getPicture(pid);
		pic.setClick_count(pic.getClick_count() + 1);
		pictureDao.saveOrUpdate(pic);
		return pic;
	}

	public List<Topic> getTopicsByCat(TopicType cat) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cat", cat);
		return topicDao.find("from Topic t where t.topicTpye =:cat", params);
	}

	public Picture getPictureByTitle(String title) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		return pictureDao.find("from Picture p where p.title=:title", params)
				.get(0);
	}

}
