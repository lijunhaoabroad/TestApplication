package com.example.lijunhao.testapplication;
        import android.provider.ContactsContract;

        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseException;
        import com.google.firebase.database.DatabaseReference;
        import com.example.lijunhao.testapplication.DataProduct;
        import java.util.ArrayList;
/**
 * Created by Oclemy on 6/21/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 * 1.SAVE DATA TO FIREBASE
 * 2. RETRIEVE
 * 3.RETURN AN ARRAYLIST
 */
public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved;
    ArrayList<DataProduct> products=new ArrayList<>();
    /*
 PASS DATABASE REFRENCE
  */
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }
//    //WRITE IF NOT NULL
//    public Boolean save(DataProduct products)
//    {
//        if(products==null)
//        {
//            saved=false;
//        }else
//        {
//            try
//            {
//                db.child("Spacecraft").push().setValue(products);
//                saved=true;
//            }catch (DatabaseException e)
//            {
//                e.printStackTrace();
//                saved=false;
//            }
//        }
//        return saved;
//    }
    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot)
    {
        products.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            DataProduct product=ds.getValue(DataProduct.class);
            products.add(product);
        }
    }
    //RETRIEVE
    public ArrayList<DataProduct> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return products;
    }
}