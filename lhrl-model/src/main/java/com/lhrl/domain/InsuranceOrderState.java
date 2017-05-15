package com.lhrl.domain;


/**
 * 保险订单状态
 * @author liu lang
 *
 */
public enum InsuranceOrderState {

	/**
	 * 等待支付
	 */
	WAIT_PAY {
		protected void finishPay(InsuranceOrder order) {
			order.setState(PAID);
		}
		
		protected void cancel(InsuranceOrder order) {
			order.setState(CANCEL);
		}
	},
	
	/**
	 * 已支付
	 */
	PAID {
		protected void confirm(InsuranceOrder order) {
			order.setState(CONFIRMED);
		}

        @Override
        protected void expire(InsuranceOrder order) {
            order.setState(EXPIRED);
        }
    },
	
	/**
	 * 已确认
	 */
	CONFIRMED
	,
	
	/**
	 * 已取消
	 */
	CANCEL {

    },
    /**
     * 已失效(已脱保)
     */
    EXPIRED;
	protected void finishPay(InsuranceOrder order) { 
		throw new RuntimeException("error state");
	}
	
	protected void confirm(InsuranceOrder order) {
		throw new RuntimeException("error state");
	}
	
	protected void cancel(InsuranceOrder order) {
		throw new RuntimeException("error state");
	}

    protected void expire(InsuranceOrder order) {
        throw new RuntimeException("error state");
    }

}
