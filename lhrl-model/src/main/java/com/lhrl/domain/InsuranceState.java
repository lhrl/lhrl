package com.lhrl.domain;


public enum InsuranceState {

	/**
     * 询价中
     */
    ASKPRICE {
        void askPrice(Insurance insurance) {

        }

        void quote(Insurance insurance) {
            insurance.setState(QUOTED);
        }
    },
    /**
     * 报价结束
     */
    QUOTED {
        void select(Insurance insurance) {
            insurance.setState(ORDER);
        }
    },
    /**
     * 订单执行状态
     */
    ORDER {
        void select(Insurance insurance) {
            if (!insurance.getOrder().getState().equals(InsuranceOrderState.WAIT_PAY))
//            insurance.setState(ORDER);
                super.select(insurance);
        }

        @Override
        public String getStateViewName(Insurance insurance) {
            return insurance.getOrder().getState().toString();
        }
    },
    /**
     * 结束
     */
    END {
        @Override
        void end(Insurance insurance) {
            if (insurance.getOrder().getState().equals(InsuranceOrderState.CONFIRMED) || insurance.getOrder().getState().equals(InsuranceOrderState.CANCEL))
                return;
            super.end(insurance);
        }
    };

    void askPrice(Insurance insurance) {
        throw new RuntimeException("error state");
    }

    void select(Insurance insurance) {
        throw new RuntimeException("error state");
    }

    void quote(Insurance insurance) {
        throw new RuntimeException("error state");
    }

    void end(Insurance insurance) {
        throw new RuntimeException("error state");
    }

    public String getStateViewName(Insurance insurance) {
        return this.toString();
    }
}
