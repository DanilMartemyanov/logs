package backend.academy.analyzer.constant;

import lombok.Getter;

@Getter
public enum NginxParametrs {
    REMOTEADDRESS(1),
    REMOTEUSER(2),
    TIMELOCAL(3),
    REQUESTTYPE(4),
    PATHRESOURCES(5),
    STATUSCODE(6),
    BODYBYTESSENT(7),
    REFER(8),
    USERAGENT(9);

    private int value;

    NginxParametrs(int value) {
        this.value = value;
    }
}
