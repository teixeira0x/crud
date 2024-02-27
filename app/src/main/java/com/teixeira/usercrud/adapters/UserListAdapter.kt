package com.teixeira.usercrud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teixeira.usercrud.R
import com.teixeira.usercrud.databinding.LayoutUserItemBinding
import com.teixeira.usercrud.domain.model.user.User

class UserListAdapter(
  private val users: List<User>,
  val onMenuClick: (view: View, user: User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

  class UserViewHolder(internal val binding: LayoutUserItemBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): UserViewHolder {
    return UserViewHolder(
      LayoutUserItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    holder.binding.apply {
      val user = users[position]
      txtName.text = user.displayName
      txtUsername.text =
        root.context.getString(R.string.username, user.username)
      txtEmail.text = root.context.getString(R.string.email, user.email)

      imgMenu.setOnClickListener { onMenuClick(it, user) }
    }
  }

  override fun getItemCount() = users.size
}
