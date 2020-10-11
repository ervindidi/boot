package com.zy.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsRecommendGetRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsSearchRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsRecommendGetResponse;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsSearchResponse;

public class PDD {
    public static void main(String[] args) throws Exception {
        String clientId = "71860ff559384ceaa2a44e327e0b9615";
        String clientSecret = "53e26f72c796c14e534b5d521951cf204983bd6f";
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddDdkGoodsRecommendGetRequest request = new PddDdkGoodsRecommendGetRequest();
//        request.setChannelType(0);
//        request.setCustomParameters("str");
//        request.setLimit(0);
//        request.setListId("str");
//        request.setOffset(0);
//        request.setPid("str");
//        request.setCatId(0L);
//        List<Long> goodsIds = new ArrayList<Long>();
//        goodsIds.add(0L);
//        request.setGoodsIds(goodsIds);
        PddDdkGoodsRecommendGetResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }
}
