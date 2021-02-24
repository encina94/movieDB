package com.brubank.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brubank.movies.utils.Constants
import com.brubank.movies.utils.Constants.Companion.SIZE_IMAGE
import com.brubank.movies.viewmodel.EmptyResponse
import com.brubank.movies.viewmodel.ErrorResponse
import com.brubank.movies.viewmodel.Loading
import com.brubank.movies.viewmodel.SuccessResponse
import com.brubank.movies.viewmodel.movies.MoviesViewModel

/**
 * Actividad principal (navigation host)
 *
 * @see "https://developer.android.com/guide/navigation/navigation-getting-started#add_a_navhost_to_an_activity"
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
    }
}