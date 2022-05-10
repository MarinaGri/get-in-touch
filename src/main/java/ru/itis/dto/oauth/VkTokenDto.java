package ru.itis.dto.oauth;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VkTokenDto {

    @SerializedName("user_id")
    private Long userId;

    private String email;

    @SerializedName("access_token")
    private String accessToken;

}
