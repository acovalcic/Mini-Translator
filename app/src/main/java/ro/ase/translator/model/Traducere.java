package ro.ase.translator.model;

import java.io.Serializable;

public class Traducere implements Serializable {

    private String textTradus;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Traducere() {
    }

    public Traducere(String textTradus) {
        this.textTradus = textTradus;
    }

    public String getTextTradus() {
        return textTradus;
    }

    public void setTextTradus(String textTradus) {
        this.textTradus = textTradus;
    }

    @Override
    public String toString() {
        return "Traducere{" +
                "textTradus='" + textTradus + '\'' +
                '}';
    }
}
