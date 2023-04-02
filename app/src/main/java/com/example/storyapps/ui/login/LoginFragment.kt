package com.example.storyapps.ui.login

import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.storyapps.R
import com.example.storyapps.databinding.FragmentLoginBinding
import com.example.storyapps.ui.component.TextObserver
import com.example.storyapps.utils.ScreenUtils

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.apply {
            edtEmail.setTextHint(getString(R.string.user_email))
            edtEmail.setTextHintColor(
                ContextCompat.getColor(requireContext(), R.color.grey)
            )
            edtPassword.setTextHint(getString(R.string.user_password))
            edtPassword.setTextHintColor(
                ContextCompat.getColor(requireContext(), R.color.grey)
            )
            edtEmail.setColorText(
                ContextCompat.getColor(requireContext(), R.color.emperor)
            )
            edtPassword.setColorText(
                ContextCompat.getColor(requireContext(), R.color.emperor)
            )
            edtEmail.setSizeText(
                ScreenUtils.convertDpToPixel(requireContext(), 14F)
            )
            edtPassword.setSizeText(
                ScreenUtils.convertDpToPixel(requireContext(), 14F)
            )
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_input_field_enabled)?.let {
                edtEmail.setRoundedBackground(it)
                edtPassword.setRoundedBackground(it)
            }

            edtEmail.onTextListener(object : TextObserver {
                override fun textChangeCallback(text: Editable?) {
                    text?.let {
                       edtEmail.error = when {
                            it.isEmpty() -> {
                                requireContext().getString(R.string.email_must_be_filled_in)
                            }
                            it.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(it).matches() -> {
                                requireContext().getString(R.string.email_not_valid)
                            }
                            else -> {
                                ""
                            }
                        }
                    }
                }
            })
        }
    }
}