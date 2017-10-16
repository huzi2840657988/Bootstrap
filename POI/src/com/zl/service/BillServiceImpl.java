package com.zl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.bean.Bill;
import com.zl.dao.BillMapper;


@Service
public class BillServiceImpl implements IBillService {
	@Resource
	private BillMapper billDao;
	/* (non-Javadoc)
	 * @see com.zl.service.IBillService#query()
	 */
	public List<Bill> query(){
		return billDao.query();
	}
}
