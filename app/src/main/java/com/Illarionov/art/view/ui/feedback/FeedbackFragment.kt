package com.Illarionov.art.view.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.Illarionov.art.R
import com.Illarionov.art.databinding.FeedbackFragmentBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val NAME_OF_COLLECTION = "feedback"
private const val COMMENT = "comment"

class FeedbackFragment : Fragment() {
    private var binding: FeedbackFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FeedbackFragmentBinding.inflate(inflater, container, false)
        setupFirestore()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupFirestore() {
        val firestore = Firebase.firestore
        binding?.let { binding ->
            binding.sendButton.setOnClickListener {
                val comment = binding.feedBackEditText.text.toString()
                if (comment.isBlank()) {
                    return@setOnClickListener
                }
                firestore.collection(NAME_OF_COLLECTION)
                    .add(
                        hashMapOf(COMMENT to comment)
                    )
                    .addOnSuccessListener { showSuccessMessage() }
                    .addOnFailureListener { showErrorMessage(it) }

                clearEditText()
            }
        }
    }

    private fun showErrorMessage(it: Exception) {
        Toast.makeText(
            activity,
            """${resources.getString(R.string.feedback_failure)}$it""",
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun clearEditText() = binding?.feedBackEditText?.setText("")

    private fun showSuccessMessage() {
        Toast.makeText(activity, resources.getString(R.string.feedback_success), Toast.LENGTH_LONG)
            .show()
    }
}