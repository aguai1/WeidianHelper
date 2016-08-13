package com.aguai.weidian.lifelogic.repositories.entities;


public class TokenInfo extends ResponseHeaderEntity{

    public Token getResult() {
        return result;
    }

    public void setResult(Token result) {
        this.result = result;
    }

    private Token result;

    public class Token{
        private String access_token;
        private int expire_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpire_in() {
            return expire_in;
        }

        public void setExpire_in(int expire_in) {
            this.expire_in = expire_in;
        }
    }
}
