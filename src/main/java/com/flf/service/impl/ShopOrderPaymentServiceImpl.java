package com.flf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.Page;
import com.flf.entity.ShopOrderPayment;
import com.flf.entity.ShopOrderPaymentVo;
import com.flf.mapper.ShopOrderPaymentMapper;
import com.flf.service.ShopOrderPaymentService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderPaymentService<br>
 */
@Service("shopOrderPaymentService")
public class ShopOrderPaymentServiceImpl extends BaseServiceImpl implements ShopOrderPaymentService {
	private final static Logger log = Logger.getLogger(ShopOrderPaymentServiceImpl.class);

	@Autowired
	private ShopOrderPaymentMapper dao;

	@Override
	public ShopOrderPaymentMapper getDao() {
		return dao;
	}

	@Override
	public int getCount(ShopOrderPayment dto) {
		return dao.getCount(dto);
	}

	@Override
	public List<ShopOrderPaymentVo> list(ShopOrderPaymentVo vo) {
		vo.setPage(vo.getPage() == null ? new Page() : vo.getPage());
		return dao.list(vo);
	}

}
