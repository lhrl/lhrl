package com.lhrl;

import java.util.List;

import org.junit.Test;

import com.lhrl.common.dao.DaoFactory;
import com.lhrl.domain.Insurance;

public class ConnectionTest extends TestSupport{

	/*private ApplicationContext acx;
	@Before
	public void before(){
		acx=new ClassPathXmlApplicationContext(new String[]{"spring/lhrl_context.xml"});
		System.out.println("junit start");
	}
	@After
	public void after(){
		acx=null;
		System.out.println("junit end");
	}
	*/
	@Test
	public void getConnection(){
		List<Insurance>insurances=DaoFactory.getDao().find(Insurance.class, null);
		for (Insurance insurance : insurances) {
			System.out.println(insurance.getOrderNo());
		}
		
	}
}
