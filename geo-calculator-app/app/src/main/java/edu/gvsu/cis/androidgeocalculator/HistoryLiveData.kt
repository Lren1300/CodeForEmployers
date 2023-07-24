package edu.gvsu.cis.androidgeocalculator

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import edu.gvsu.cis.androidgeocalculator.placeholder.HistoryContent

class HistoryLiveData(val topRef: CollectionReference) :
    LiveData<List<HistoryContent.HistoryItem>>()
{

    lateinit var listener: ListenerRegistration

    override fun onActive() {
        listener = topRef.addSnapshotListener(docListener)
    }

    override fun onInactive() {
        listener.remove()
    }


    val docListener = EventListener<QuerySnapshot>() { snapShot, error ->
        if (error != null) {
            return@EventListener
        }
        snapShot?.let {
            val all = ArrayList<HistoryContent.HistoryItem>()
            it.documentChanges.forEach {
                Log.d("ConversionCalculator", "${it.type.name} => ${it.document.data}")
                all.add(it.document.toObject(HistoryContent.HistoryItem::class.java))
            }
            postValue(all)
        }
    }
}
