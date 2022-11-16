package com.example.myapplicationrecipe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicationrecipe.DAO.FoodDAO;
import com.example.myapplicationrecipe.Database.AppDataBase;
import com.example.myapplicationrecipe.databinding.FragmentFirstBinding;
import com.example.myapplicationrecipe.entity.Food;

import java.io.IOException;
import java.net.URL;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText name;
    private EditText des;
    private Button save;
    //private FoodDAO foodDAO ;
    private AppDataBase database;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        database =database.getAppDatabase(getContext());

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
        //Button button1 = view.findViewById(R.id.tackephoto);
        imageView = view.findViewById(R.id.image_addfood);
//        button1.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view) {
//                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//              //  startActivityForResult(cInt,CAMERA_INTENT);
//            }
//        });
        Button button2= view.findViewById(R.id.select_image);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imageChooser();
                //  System.out.println( database.foodDAO().getAll().size());

            }
        });
         save= view.findViewById(R.id.btnsave);
        name= view.findViewById(R.id.text_name);
        des= view.findViewById(R.id.text_description);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            savefood();

            }
        });

        binding.cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cancel();

//
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        super.onViewCreated(view, savedInstanceState);

    }
    public void cancel(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container , SecondFragment.class,null);
        fragmentTransaction.commit();
    }
    ImageView imageView;
    public void savefood(){
        if(name.getText().toString().isEmpty() || des.getText().toString().isEmpty() ){
           Toast.makeText ( getActivity() ,"recipe not empty",Toast.LENGTH_SHORT).show();
       }
       else {
            Food food = new Food();
            food.setName(name.getText().toString());
            food.setDescription(des.getText().toString());
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            food.setImage(DataConverter.convert_image2ByteArray(bitmap));
            System.out.println(food.getImage());
            database.foodDAO().insertOne(food);
            Toast.makeText ( getActivity() ,"recipe add with successfully",Toast.LENGTH_SHORT).show();
            cancel();
        }
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    int SELECT_PICTURE = 200;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
//                Uri selectedImageUri = Uri.parse(data.getData().toString());
//                System.out.println("hhhhhhhhhhhhhhhhhh:"+ selectedImageUri);
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    imageView.setImageURI(selectedImageUri);
//                }
//                Uri imageUri = data.getData();
//                    if( requestCode == Activity.RESULT_OK){
//                        Bitmap  bmpImage = (Bitmap) data.getExtras().get("data");
//                        if(bmpImage != null)
//                        imageView.setImageBitmap(bmpImage);
//
//                    }
                Uri uri ;
                if(resultCode == RESULT_OK) {

                    uri = data.getData();
                    imageView.setImageURI(uri);

                }
else Toast.makeText ( getActivity() ,"You",Toast.LENGTH_SHORT).show();

                    //     imageView.setImageURI(selectedImageUri);

            }

     //   }
    }}

//    final int CAMERA_INTENT = 51;
//    public void takePicture(View view) {
//        Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        startActivityForResult(cInt,CAMERA_INTENT);
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//        @Override
//        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == CAMERA_INTENT) {
//                if (resultCode == RESULT_OK) {
//                    Bitmap bp = (Bitmap) data.getExtras().get("data");
//                    imageView.setImageBitmap(bp);
//                } else if (resultCode == RESULT_CANCELED) {
//                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
//                }
//            }}

    }