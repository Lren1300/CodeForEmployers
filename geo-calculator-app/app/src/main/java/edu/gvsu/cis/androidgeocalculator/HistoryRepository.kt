package edu.gvsu.cis.androidgeocalculator

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.gvsu.cis.androidgeocalculator.placeholder.HistoryContent


class HistoryRepository {
        private val dbStore = Firebase.firestore
        private var collRef: CollectionReference? = dbStore.collection("history")

        fun firebaseAddHistory(d: HistoryContent.HistoryItem) {
                val jData = hashMapOf(
                        "origLat" to d.origLat, "origLng" to d.origLng,
                        "destLat" to d.destLat, "destLng" to d.destLng,
                        "timestamp" to d.timestamp
                )

                collRef?.let {
                        it.add(jData)
                }
        }
        val historyLiveData by lazy {
                val coll = dbStore.collection("history")
                HistoryLiveData(coll)
        }



}