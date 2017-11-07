package com.attozoic.muzejirade.dataService;

import android.util.Log;

import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nenadicivan on 9/30/2017.
 */

public class PostServiceFireBase implements PostServiceFireBaseInterface{
    @Override
    public void getItems(FireBaseDatabaseListener callback, String page) {


        Log.d("fatala","service se pali");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = page == null ? mDatabase.child("posts").orderByChild("createdAt").limitToLast(20) : mDatabase.child("posts").orderByChild("createdAt").endAt(page, "createdAt").limitToLast(20);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("fatala","data se pali");
                List<Post> posts = new ArrayList<Post>();

                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        posts.add(0, postSnapshot.getValue(Post.class));
                    }
                }

                 callback.onSuccess((List) posts);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
                Log.d("fatala","error se pali");
            }
        });

    }

    @Override
    public void getItems2(FireBaseDatabaseListener callback) {
        List<Museum> listOfMuseums = new ArrayList<>();

        listOfMuseums.add(new Museum(44.81676154700503, 20.459753187696833, "Narodni muzej u Beogradu","http://www.narodnimuzej.rs/"));
        listOfMuseums.add(new Museum(44.81959789056077, 20.442450082556206, "Muzej savremene umetnosti, Beograd", "http://msub.org.rs/"));

        listOfMuseums.add(new Museum(44.81702155912503, 20.454471411361737, "Muzej primenjene umetnosti", "http://www.mpu.rs/"));
        listOfMuseums.add(new Museum(44.8024132831466, 20.47114905840533, "Prirodnjački muzej", "http://www.nhmbeo.rs/pocetna.1.html"));
        listOfMuseums.add(new Museum(44.81986939999999, 20.456278800000064, "Etnografski muzej", "http://etnografskimuzej.rs/" ));
        listOfMuseums.add(new Museum(44.81251604138674, 20.463801217196533, "Istorijski muzej Srbije", "http://www.imus.org.rs/"));
        listOfMuseums.add(new Museum(44.82174281081473, 20.465134632679792, "Muzej nauke i tehnike", "http://www.muzejnt.rs/site/home/"));

        listOfMuseums.add(new Museum(44.78850948132359, 20.452588505804442, "Muzej istorije Jugoslavije Beograd", "http://mij.rs/"));
        listOfMuseums.add(new Museum(44.813605066454706,20.464541166270465, "Muzej jugoslovenske kinoteke Beograd", "http://www.kinoteka.org.rs/"));
        listOfMuseums.add(new Museum(44.78907164372715, 20.43564836656492, "Muzej afričke umetnosti", "http://www.museumofafricanart.org/sr.html"));
        listOfMuseums.add(new Museum(44.8213149, 20.458621006745943, "Muzej pozorišne umetnosti Srbije", "http://mpus.org.rs/"));
        listOfMuseums.add(new Museum(44.82089377874318, 20.454697633729552, "Pedagoški muzej Beograd", "http://www.pedagoskimuzej.org.rs/"));
        listOfMuseums.add(new Museum(44.80511976342688, 20.470747504368546, "Muzej Nikole Tesle Beograd", "http://www.tesla-museum.org/"));

        listOfMuseums.add(new Museum(44.821957607188864, 20.449879148618265, "Vojni muzej Beograd", "http://www.muzej.mod.gov.rs/"));
        listOfMuseums.add(new Museum(44.771352, 20.467373 , "Muzej banjičkog logora", "http://www.mgb.org.rs/stalne-postavke/muzej-banjickog-logora"));
        listOfMuseums.add(new Museum(44.80970700216858, 20.463814856518184, "Muzej Ive Andrića", "http://www.mgb.org.rs/stalne-postavke/muzej-ive-andrica"));

        listOfMuseums.add(new Museum(44.816776768315364, 20.459726365606684, "Muzej Vuka i Dositeja", "http://www.narodnimuzej.rs/o-muzeju/prostori-narodnog-muzeja/muzej-vuka-i-dositeja/"));
        listOfMuseums.add(new Museum(44.807256, 20.46414989999994, "Muzej Paje Jovanovića", "http://www.mgb.org.rs/stalne-postavke/muzej-paje-jovanovica"));

        listOfMuseums.add(new Museum(44.7651116, 20.44449480000003, "Konak kneza Miloša", "http://www.imus.org.rs/konak.php?jz=0"));
        listOfMuseums.add(new Museum(44.817146844287244, 20.452328692065407, "Konak Kneginje Ljubice", "http://www.mgb.org.rs/stalne-postavke/konak-kneginje-ljubice"));

        listOfMuseums.add(new Museum(44.81675964434096, 20.45973441223373, "Galerija fresaka", "http://www.narodnimuzej.rs/o-muzeju/prostori-narodnog-muzeja/galerija-fresaka/"));
        listOfMuseums.add(new Museum(44.8179002892914, 20.451583037959267, "Muzej srpske pravoslavne crkve", "http://www.spc.rs/"));
        listOfMuseums.add(new Museum(44.81958266999513, 20.442450082556206, "Legat Milice Zorić i Rodoljuba Čolakovića", "http://www.msub.org.rs/o-legatu-colakovic"));
        listOfMuseums.add(new Museum(44.81958219667394, 20.442434587727348, "Salon Muzeja Savremene umetnosti", "http://www.msub.org.rs/o-salonu"));
        listOfMuseums.add(new Museum(44.819854179506024, 20.456439732540957, "Manakova kuća", "http://etnografskimuzej.rs/rs/manakova-kuca/"));
        listOfMuseums.add(new Museum(44.763333619045746, 20.42823556018834, "Muzej fizičke kulture", "http://www.nadji.info/mesto/12425/muzej-fizicke-kulture/"));
        listOfMuseums.add(new Museum(44.783212334037415, 20.463723105835015, "Muzej FK „Crvena zvezda“", "http://www.crvenazvezdafk.com/sr/stadion/muzej.html"));

        listOfMuseums.add(new Museum(44.7887588, 20.459041399999933, "Muzej FK “Partizan”", "http://www.sr.partizan.rs/poseta-stadionu/"));
        listOfMuseums.add(new Museum(44.81902304203122, 20.285596460144006, "Muzej vazduhoplovstva", "http://www.muzejvazduhoplovstva.org.rs/"));
        listOfMuseums.add(new Museum(44.8125464680984, 20.468018555294748, "PTT muzej", "http://www.pttmuzej.rs/"));
        listOfMuseums.add(new Museum(44.80623004913434, 20.458991746696483, "Železnički muzej", "http://www.zeleznicesrbije.com/active/sr-latin/home/glavna_navigacija/kultura_nauka_i_sport/zeleznicki_muzej.html"));
        listOfMuseums.add(new Museum(44.81389688419573, 20.466051404893506, "Muzej automobila", "http://www.automuseumbgd.com/"));
        listOfMuseums.add(new Museum(44.82124573407569, 20.45685412268449, "Jevrejski istorijski muzej Beograd", "http://www.jimbeograd.org/"));
        listOfMuseums.add(new Museum(44.8163314, 20.46952220000003, "Muzej srpske medicine Beograd", "http://www.sld.org.rs/sr/muzej.asp"));
        listOfMuseums.add(new Museum(44.74715030697828, 20.493468729052665, "Muzej za istoriju farmacije", "http://www.pharmacy.bg.ac.rs/fakultet/"));
        listOfMuseums.add(new Museum(44.818557531123965, 20.45594870290222, "Muzej Zepter", "http://www.zeptermuseum.rs/"));
        listOfMuseums.add(new Museum(44.813347411107294, 20.518464086508175, "Muzej romske kulture", "http://www.romamuseum.rs/onama.html"));
        listOfMuseums.add(new Museum(44.79176852606818, 20.434867149073852, "Vespa muzej Srbija", "http://vespamuzejsrbija.rs/"));
        listOfMuseums.add(new Museum(44.814119456190866, 20.467969194967623, "Dom Jevrema Grujića", "http://domjevremagrujica.com/"));

               callback.onSuccess(listOfMuseums);
    }
}
