package com.example.load_github_profiles_in_recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvName, mTvUserId;
    private ImageView mIvAvatar;
    public ProfileViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        mIvAvatar = itemView.findViewById(R.id.IvAvatar);
        mTvName = itemView.findViewById(R.id.tvName);
        mTvUserId = itemView.findViewById(R.id.etUsername);
    }

    public void setData(ResponseModel responseModel){
        Glide.with(mIvAvatar).load(responseModel.getOwner().getAvatarUrl()).into(mIvAvatar);
        mTvName.setText(responseModel.getName());
        mTvUserId.setText(responseModel.getOwner().getLogin());
    }
}
