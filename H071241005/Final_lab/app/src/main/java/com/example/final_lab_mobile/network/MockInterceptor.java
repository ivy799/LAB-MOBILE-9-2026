package com.example.final_lab_mobile.network;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String json = "{\n" +
                "  \"products\": [\n" +
                "    { \"id\": 1, \"title\": \"Tenda Dome Kapasitas 2 Orang\", \"category\": \"Tents\", \"description\": \"Tenda double layer anti air dengan sirkulasi udara yang baik. Cocok untuk pendakian gunung beriklim tropis.\", \"price\": 45.99, \"rating\": 4.8, \"thumbnail\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwPhYM8jLvjXJE_i_RzN0pDY-C7SUfueIgVA&s\" },\n" +
                "    { \"id\": 2, \"title\": \"Tenda Family Kapasitas 6 Orang\", \"category\": \"Tents\", \"description\": \"Tenda berukuran luas untuk keluarga dengan ruang tamu terpisah dan material tahan angin kencang.\", \"price\": 120.00, \"rating\": 4.6, \"thumbnail\": \"https://image.made-in-china.com/202f0j00VCBbqPIhlkcF/Family-Outdoor-Portable-Sunshade-Camping-Picnic-Tent-for-6-Persons.webp\" },\n" +
                "    { \"id\": 3, \"title\": \"Carrier Bag 45L\", \"category\": \"Backpacks\", \"description\": \"Tas gunung kapasitas menengah dengan backsystem nyaman untuk pendakian 1-2 hari.\", \"price\": 55.50, \"rating\": 4.7, \"thumbnail\": \"https://id-live-01.slatic.net/p/9140f21950fee27ad5e290cda6e805e1.jpg\" },\n" +
                "    { \"id\": 4, \"title\": \"Carrier Bag 60L + Raincover\", \"category\": \"Backpacks\", \"description\": \"Ransel ekspedisi dengan kompartemen luas, jahitan kuat, dan sudah termasuk jas hujan tas.\", \"price\": 75.00, \"rating\": 4.9, \"thumbnail\": \"https://www.eigeradventure.com/blog/wp-content/uploads/2025/10/R.-Wanderlust-60L-1.jpg\" },\n" +
                "    { \"id\": 5, \"title\": \"Daypack Lipat 20L\", \"category\": \"Backpacks\", \"description\": \"Tas kecil yang bisa dilipat menjadi seukuran kepalan tangan. Sangat ringan untuk summit attack.\", \"price\": 15.99, \"rating\": 4.5, \"thumbnail\": \"https://down-id.img.susercontent.com/file/id-11134207-7rasg-m4lagutjkjuc45\" },\n" +
                "    { \"id\": 10, \"title\": \"Sepatu Hiking Pria Waterproof\", \"category\": \"Footwear\", \"description\": \"Sepatu gunung dengan grip outsole kuat dan membran tahan air untuk segala medan.\", \"price\": 65.00, \"rating\": 4.8, \"thumbnail\": \"https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//catalog-image/100/MTA-117790317/snta_sepatu_gunung-hiking_outdoor_snta_505_grey_ltblue_boots_trekking_climbing_mendaki_gunung_gratis_ongkir_terbaru_stok_terbatas_sports_climbing_shoes_full01_dchc1tuf.jpg\" },\n" +
                "    { \"id\": 11, \"title\": \"Sepatu Trekking Wanita Mid-Cut\", \"category\": \"Footwear\", \"description\": \"Sepatu hiking desain khusus wanita, melindungi engkel dengan bantalan insole yang empuk.\", \"price\": 62.50, \"rating\": 4.7, \"thumbnail\": \"https://down-id.img.susercontent.com/file/id-11134207-7r98o-lubgjzaq39au6d\" },\n" +
                "    { \"id\": 12, \"title\": \"Sandal Gunung Tali Kuat\", \"category\": \"Footwear\", \"description\": \"Sandal tangguh untuk bersantai di area camp atau trekking ringan melintasi sungai.\", \"price\": 20.00, \"rating\": 4.5, \"thumbnail\": \"https://antarestar.com/wp-content/uploads/2024/04/SENDAL-ARCHER-BARU-FOTO-MALL-5.webp\" },\n" +
                "    { \"id\": 6, \"title\": \"Sleeping Bag Model Mummy\", \"category\": \"Others\", \"description\": \"Kantong tidur dengan isolasi thermal tinggi. Mampu menahan suhu hingga 5 derajat celcius.\", \"price\": 35.00, \"rating\": 4.8, \"thumbnail\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZWXVy5v_KcNxfMesPzo8N5sL_x4lOn8hjug&s\" }\n" +
                "  ]\n" +
                "}";

        return new Response.Builder()
                .code(200)
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"), json.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }
}
