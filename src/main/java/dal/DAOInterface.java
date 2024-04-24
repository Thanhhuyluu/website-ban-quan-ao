package dal;

import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(int id);
	public List<T> selectAll();
	public T selectById(int id);
	public ArrayList<T> selectByCondition(String condition);
	
}
