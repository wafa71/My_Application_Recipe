package com.example.myapplicationrecipe.Listener;

import com.example.myapplicationrecipe.Models.Root;

public interface RandomRecipeResponseListener {
    void didFetch(Root response ,String mesg);
    void didError(String mesg);

}
