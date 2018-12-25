package com.starcode.schedule_uny.Activity;

import android.content.Intent;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.starcode.schedule_uny.R;
import com.starcode.schedule_uny.apiHolder.baseApiService;
import com.starcode.schedule_uny.apiHolder.utilsApi;
import com.starcode.schedule_uny.model.DataProfilResponse;
import com.starcode.schedule_uny.model.ScheduleResponse;
import com.starcode.schedule_uny.session.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

//public class AsyncWeekHomework_Activity extends HomeWork_Activity implements MonthLoader.MonthChangeListener,Callback<List<ScheduleResponse>> {
//
//    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
//    boolean calledNetwork = false;
//    private SessionManager sessionManager;
//    private com.starcode.schedule_uny.apiHolder.baseApiService baseApiService;
//    private static String status;
//    private static String kelas_id;
//    private static String jurusan_id;
//
//
//
//    @Override
//    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
//
//
//        if (!calledNetwork) {
//
//            baseApiService= utilsApi.getApiServices();
//            Call<DataProfilResponse> call= baseApiService.getAllProfile(sessionManager.getSpContenttype(),
//                    sessionManager.getSpAuthorization());
//            call.enqueue(new retrofit2.Callback<DataProfilResponse>() {
//                @Override
//                public void onResponse(Call<DataProfilResponse> call, retrofit2.Response<DataProfilResponse> response) {
//                    if (response.isSuccessful()){
//                        status=response.body().getAuth_user().getStatus();
//                        if (status.equals("200")){
//                            kelas_id=response.body().getAuth_user().getData().getKelas_id();
//                            jurusan_id=response.body().getAuth_user().getData().getJurusan_id();
//                        }else{
//                            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
//                            startActivity(new Intent(AsyncWeekHomework_Activity.this, Main_Activity.class).
//                                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            finish();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<DataProfilResponse> call, Throwable t) {
//
//                }
//            });
//             baseApiService.Schedule(kelas_id,jurusan_id);
//            calledNetwork = true;
//        }
//
//        // Return only the events that matches newYear and newMonth.
//        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
//        for (WeekViewEvent event : events) {
//            if (eventMatches(event, newYear, newMonth)) {
//                matchedEvents.add(event);
//            }
//        }
//        return matchedEvents;
//    }
//
//    private boolean eventMatches(WeekViewEvent event, int year, int month) {
//        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1)
//                || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
//    }
//
//    @Override
//    public void success(List<ScheduleResponse> schedule, Response response) {
//        this.events.clear();
//        for (ScheduleResponse Schedule : schedule) {
//            this.events.add(Schedule.getAuth_Schedule().getData().getSchedule().toWeekViewEvent());
//        }
//        getWeekView().notifyDatasetChanged();
//    }
//
//    @Override
//    public void failure(RetrofitError error) {
//        error.printStackTrace();
//        Toast.makeText(this, R.string.async_error, Toast.LENGTH_SHORT).show();
//    }
//}
