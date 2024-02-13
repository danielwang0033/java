package com.coin.req.dict;

import com.coin.entity.TDict;
import com.coin.req.CommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictUpdateReq extends CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TDict> updateList;
}
