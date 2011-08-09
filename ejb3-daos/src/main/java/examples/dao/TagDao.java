package examples.dao;

import examples.domain.Tag;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TagDao {
	public Tag getTag(int id);

	public List<Tag> getTagList();
}
