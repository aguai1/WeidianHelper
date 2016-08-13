package com.aguai.weidian.lifelogic.repositories;

import com.aguai.weidian.lifelogic.repositories.entities.TokenInfo;
import com.aguai.weidian.lifelogic.repositories.net.TokenNet;
import org.json.JSONException;
import java.io.IOException;
import rx.Observable;
import rx.Subscriber;

public class TokenRepo extends BaseRepo {

    private TokenNet mNet;

    public TokenRepo() {
        mNet = new TokenNet(this);
    }

    public Observable<TokenInfo> getToken(final String appkey, final String secret) {
        return Observable.create(new Observable.OnSubscribe<TokenInfo>() {
            @Override
            public void call(Subscriber<? super TokenInfo> subscriber) {
                try {
                    TokenInfo  item = mNet.getToken(appkey,secret);
                    if (item != null) {
                        subscriber.onNext(item);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(newIOException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
