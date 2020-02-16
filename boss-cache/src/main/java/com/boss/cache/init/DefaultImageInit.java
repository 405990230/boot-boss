package com.boss.cache.init;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DefaultImageInit {

    private byte[] png404;

    @PostConstruct
    public void initLoad() {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("404.png");
            this.png404 = IOUtils.toByteArray(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getPng404() {
        return png404;
    }

    public void setPng404(byte[] png404) {
        this.png404 = png404;
    }

}
