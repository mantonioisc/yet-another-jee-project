package examples.dao.impl;

import examples.dao.TagDao;
import examples.domain.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TagDaoBean implements TagDao {
	private static final Log log = LogFactory.getLog(TagDaoBean.class);

	@PersistenceContext(name = "games")
	EntityManager em;

	@Override
	public Tag getTag(int id) {
		log.debug("Entering [getTag] method ");

		Tag tag = em.find(Tag.class, id);

		log.debug(tag);

		log.debug("Finishing [getTag] method ");
		return tag;
	}

	@Override
	public List<Tag> getTagList() {
		log.debug("Entering [getTagList] method ");

		List<Tag> tagList = em.createQuery("SELECT t FROM Tag AS t", Tag.class).getResultList();

		log.debug("Tags# " + tagList.size());

		log.debug("Finishing [getTagList] method ");
		return tagList;
	}
}
