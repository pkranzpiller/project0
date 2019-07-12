package customer;

import java.util.List;

import shared.Dao;

public class CustomerDao implements Dao {

	@Override
	public void insert(Object e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getAll() {
		// leave blank because customers shouldn't be able to see all
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkIfExists() {
		
	}

}
