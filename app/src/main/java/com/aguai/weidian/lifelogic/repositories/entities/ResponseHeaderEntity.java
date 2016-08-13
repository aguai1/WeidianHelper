package com.aguai.weidian.lifelogic.repositories.entities;

public class ResponseHeaderEntity {

    protected Status status;
    /**
     * retcode : 1
     * msg : 查询成功
     * errorCode : 0
     */
    public class Status{
        private String status_reason;
        private int status_code;

        public String getStatus_reason() {
            return status_reason;
        }

        public void setStatus_reason(String status_reason) {
            this.status_reason = status_reason;
        }

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
