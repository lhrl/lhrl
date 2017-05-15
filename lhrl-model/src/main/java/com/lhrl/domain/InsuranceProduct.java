package com.lhrl.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuanter.common.domain.Product;
import com.kuanter.common.domain.SystemType;

public enum InsuranceProduct implements Product<Long>{
	
	 /**
     * 交强险
     */
    JIAOQIANGXIAN {
        @Override
        public Long getId() {

            return 1L;
        }

        @Override
        public String getName() {

            return "交强险";
        }

        @Override
        public Type getType() {

            return Type.FORCED;
        }

    },

    DISANZHE {
        @Override
        public Long getId() {

            return 2L;
        }

        @Override
        public String getName() {

            return "第三者责任险";
        }

        public String[] getOptions() {
            String[] selection = new String[]{"5万", "10万", "15万", "20万", "30万", "50万", "100万"};
            return selection;
        }

        @Override
        public Type getType() {

            return Type.BIZ;
        }

    },

    CHESUN {
        @Override
        public Long getId() {
            return 3L;
        }

        @Override
        public String getName() {
            return "车损险";
        }

        @Override
        public Type getType() {

            return Type.BIZ;
        }

    },

    DAOQIANG {
        @Override
        public Long getId() {
            return 4L;
        }

        @Override
        public String getName() {
            return "盗抢险";
        }

        @Override
        public Type getType() {

            return Type.BIZ;
        }
    },

    HUAHENG {
        @Override
        public Long getId() {
            return 5L;
        }

        @Override
        public String getName() {
            return "划痕险";
        }

        public String[] getOptions() {
            String[] selection = new String[]{"2000", "5000", "10000", "20000"};
            return selection;
        }

        @Override
        public Type getType() {

            return Type.BIZ;
        }

    },

    BUJIMIANPEI {
        @Override
        public Long getId() {
            return 6L;
        }

        @Override
        public String getName() {
            return "不计免赔险";
        }

        public String[] getOptions() {
            String[] selection = new String[]{"三者", "车损", "司机", "乘客", "自燃", "划痕", "涉水", "盗抢"};
            return selection;
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }

    },

    CHECHUANSHUI {
        @Override
        public Long getId() {
            return 7L;
        }

        @Override
        public String getName() {
            return "车船税";
        }

        @Override
        public Type getType() {
            return Type.FORCED;
        }
    },

    SIJIZEREN {
        @Override
        public Long getId() {
            return 8L;
        }

        @Override
        public String getName() {
            return "车上人员责任险(司机)";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }
    },

    CHENGKEZEREN {
        @Override
        public Long getId() {
            return 9L;
        }

        @Override
        public String getName() {
            return "车上人员责任险(乘客)";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }
    },

    BOLIPOSUI {
        @Override
        public Long getId() {
            return 10L;
        }

        @Override
        public String getName() {
            return "玻璃单独破碎险";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }
    },

    ZIRANSUISHI {
        @Override
        public Long getId() {
            return 11L;
        }

        @Override
        public String getName() {
            return "自燃损失险";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }
    },

    FADONGJISHESHUI {
        @Override
        public Long getId() {
            return 12L;
        }

        @Override
        public String getName() {
            return "发动机涉水损失险";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }
    },

    TEYUEXIAN {
        @Override
        public Long getId() {
            return 13L;
        }

        @Override
        public String getName() {
            return "特约险";
        }

        @Override
        public Type getType() {
            return Type.BIZ;
        }

    };

    public enum Type {
    	FORCED {
    		public String getName() {
    			return "强制险";
    		}

			@Override
			public String getKey() {
				return "compulsoryRate";
			}
    	}
    	, 
    	BIZ {
    		public String getName() {
    			return "商业险";
    		}

			@Override
			public String getKey() {
				return "commercialRate";
			}
    	}
    	
    	;
    	public abstract String getName();
    	public abstract String getKey();
    	}


    public abstract Type getType();

    public String[] getOptions() {
        return new String[0];
    }

    public SystemType getSystemType() {
        return SystemType.INSURANCE;
    }

    public String getKey() {
        return this.name();
    }

    public static InsuranceProduct valueOf(Long id) {
        for (InsuranceProduct product : InsuranceProduct.values()) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

   public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(InsuranceProduct.values()));
    }
	

}
