package br.com.lucas.boni.bittencourt.cursomc.resources.exception;

import java.io.Serializable;

public class StandarError implements Serializable {
    private Integer estatus;
    private String msg;
    private Long timestamp;

    public StandarError(Integer estatus, String msg, Long timestamp) {
        this.estatus = estatus;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
