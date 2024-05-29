package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import model.RefundRequest;

public class RefundRequestDAO implements DAOInterface<RefundRequest> {
	private static RefundRequestDAO instance;
	public static RefundRequestDAO getInstance() {
		if(instance == null) {
			instance = new RefundRequestDAO();
		}
		return instance;
	}
	@Override
	public int insert(RefundRequest t) {
		int ketqua = 0;
		
		return ketqua;
	}

	@Override
	public int update(RefundRequest t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(RefundRequest t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RefundRequest> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefundRequest selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RefundRequest> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
