package com.marty.yummy.worker;

import android.os.AsyncTask;

import com.marty.yummy.dbutilities.AppDatabase;
import com.marty.yummy.model.FoodDetails;

import java.util.ArrayList;
import java.util.List;

public class SaveFoodMenu extends AsyncTask<Void,Void,Void> {
    private AppDatabase db;
    private List<FoodDetails> foodDetails;
    public SaveFoodMenu(AppDatabase db,List<FoodDetails> foodDetails) {
        this.db = db;
        this.foodDetails = foodDetails;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (db != null) {
    if (foodDetails != null && !foodDetails.isEmpty()) {
        List<String> nameList = new ArrayList<>();
        for (FoodDetail foodDetail : foodDetails) {
            nameList.add(foodDetail.getName());
            foodDetail.setQuantity(db.cartItemDao().getCartCount(foodDetail.getName()));
            }
          }
        }

                db.foodDetailsDao().save(foodDetails);
                db.foodDetailsDao().deleteOtherFoods(nameList);
            }else{
                db.foodDetailsDao().deleteAll();
            }
        }
        return null;
    }
}
