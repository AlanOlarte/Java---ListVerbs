package com.alanolarte.myverblist.model;

public class Verb_List_Model {
    private int id;
    private int type;
    private String infinitive;
    private String past;
    private String participle;
    private String spanish;

    public Verb_List_Model(int id, int type, String infinitive, String past, String participle, String spanish) {
        this.id = id;
        this.type = type;
        this.infinitive = infinitive;
        this.past = past;
        this.participle = participle;
        this.spanish = spanish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getPast() {
        return past;
    }

    public void setPast(String past) {
        this.past = past;
    }

    public String getParticiple() {
        return participle;
    }

    public void setParticiple(String participle) {
        this.participle = participle;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }
}
