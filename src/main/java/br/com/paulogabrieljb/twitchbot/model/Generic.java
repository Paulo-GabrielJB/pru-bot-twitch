package br.com.paulogabrieljb.twitchbot.model;

public class Generic {

    private Long id;
    private String message;
    private String name;
    private Boolean isRandom;

    public Generic(){
    }

    public Generic(Long id, String message, String name, Boolean isRandom) {
        this.id = id;
        this.message = message;
        this.name = name;
        this.isRandom = isRandom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRandom() {
        return isRandom;
    }

    public void setRandom(Boolean random) {
        isRandom = random;
    }
}
