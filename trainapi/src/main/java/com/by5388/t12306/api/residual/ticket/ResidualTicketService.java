package com.by5388.t12306.api.residual.ticket;

import com.by5388.t12306.api.bean.QueryResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by5388  on 2019/5/4.
 */
public interface ResidualTicketService {

    @GET("lcxxcx/query")
    Observable<QueryResult<ResidualTicket>> getResidualTicket(@Query("purpose_codes") String purposeCodes,
                                                              @Query("queryDate") String date,
                                                              @Query("from_station") String fromStation,
                                                              @Query("to_station") String toStation
    );
}
