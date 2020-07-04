package com.su.shisui.auth.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author sly
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {

    private String Base64Image;

    private String sRand;

    private int num;
}
