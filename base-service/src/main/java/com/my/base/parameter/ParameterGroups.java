package com.my.base.parameter;

/**
 * 参数校验分组，以常见的增删改查等操作来命名，如有需要可自行添加
 */
public interface ParameterGroups {

    /** Group Insert */
    public interface Insert {
    }

    /** Group Query */
    public interface Query {
    }

    /** Group Delete */
    public interface Delete {
    }

    /** Group Update */
    public interface Update {
    }

    /** Group Reset */
    public interface Reset {
    }
}
