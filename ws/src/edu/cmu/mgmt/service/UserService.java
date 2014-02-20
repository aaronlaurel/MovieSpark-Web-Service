package edu.cmu.mgmt.service;

import java.util.List;
import java.util.Map;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import edu.cmu.mgmt.common.TopicType;
import edu.cmu.mgmt.model.Comment;
import edu.cmu.mgmt.model.Picture;
import edu.cmu.mgmt.model.Topic;
import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.vo.CategoryVo;
import edu.cmu.mgmt.model.vo.PictureVo;

public interface UserService {

	public List<Picture> getPictures(int topic_id);

	public List<Topic> getHotestTopics();

	public List<Topic> getLatestTopics();

	public Topic createTopic(Topic topic, Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters,
			String userid, String movie);

	public Picture createPhoto(Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters,
			String userid, Integer topicId, String movie);

	public List<Oembed> submitComment(Token accessToken, int pid,
			String content, OAuthService twitterOAuthService);

	public List<Comment> getCommentListByPid(int pid);

	public List<Oembed> getOemTweets(Token accessToken,
			OAuthService twitterOAuthService, int pid);

	public void savePicture(Picture pic);

	public Topic saveOrUpdateTopic(Topic topic);

	public void saveComment(Comment comment);

	public Topic getTopicById(int tid);

	public Topic getTopicByTitle(String title);

	public Picture getPicture(int pid);

	public Picture getPictureByFlickrId(String flickr_id);

	public int getPicturesAmountByTopic(int topic_id);

	public List<Picture> getPicturesByTopic(int topic_id);

	public int getCommentsAmountByTopic(int topic_id);

	public List<Topic> getTopics();

	public List<PictureVo> getPictureVo();

	public int getPictureComments(int pid);

	public List<CategoryVo> getCategoryStat();

	public Picture updateClick(int pid);

	public List<Topic> getTopicsByCat(TopicType cat);

	public Picture getPictureByTitle(String title);
}
