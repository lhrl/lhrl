package com.lhrl.repository;

/**
 * 
 * @author liu lang
 *
 */
public enum InsuranceProvider {

	PINGAN {
		@Override
		public String getShortName() {
			return "平安";
		}

		@Override
		public String getFullName() {
			return "中国平安保险股份有限公司";
		}
	},
	
	PEOPLE {
		@Override
		public String getShortName() {
			return "人保";
		}

		@Override
		public String getFullName() {
			return "中国人民财产保险股份有限公司";
		}
	},
	
	PACIFIC {
		@Override
		public String getShortName() {
			return "太保";
		}

		@Override
		public String getFullName() {
			return "中国太平洋保险股份有限公司";
		}
	},
	;
	
	public abstract String getShortName();
	
	public abstract String getFullName();
	
	public String getKey() {
		return this.name();
	}
}
