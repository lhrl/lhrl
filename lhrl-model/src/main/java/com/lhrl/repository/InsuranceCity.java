package com.lhrl.repository;
/**
 * 保险城市
 * @author liu lang
 *
 * 2017��5��4������2:40:55
 */
public enum InsuranceCity {

	BEIJING{

		@Override
		public String getName() {
			
			return "北京";
		}
		
		
	}
	,
	SHANGHAI{

		@Override
		public String getName() {
			
			return "上海";
		}
		
		
	}
	,
	
	GUANGZHOU
	{

		@Override
		public String getName() {
			
			return "广州";
		}
		
	}
	,
	WUHAN
	{

		@Override
		public String getName() {
			
			return "武汉";
		}

		
	}

	;
	
	 public abstract  String getName();
	
	public String getKey() {
		return this.name();
	}
	
	
}
