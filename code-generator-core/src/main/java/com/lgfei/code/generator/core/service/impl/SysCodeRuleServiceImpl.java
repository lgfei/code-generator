package com.lgfei.code.generator.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.common.exception.BaseRuntimeException;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import com.lgfei.code.generator.common.entity.SysCodeRule;
import com.lgfei.code.generator.core.mapper.SysCodeRuleMapper;
import com.lgfei.code.generator.core.service.ISysCodeRuleService;

/**
 * <p>
 * 编码规则表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-08-25
 */
@Service
public class SysCodeRuleServiceImpl extends BaseServiceImpl<SysCodeRuleMapper, SysCodeRule, Long> 
    implements ISysCodeRuleService {

    @Override
    public String getNextNo(String noTable, String noField) {
        SysCodeRule sysCodeRule = new SysCodeRule();
        sysCodeRule.setNoTable(noTable);
        sysCodeRule.setNoField(noField);
        Wrapper<SysCodeRule> queryWrapper = new QueryWrapper<>(sysCodeRule);
        List<SysCodeRule> sysCodeRuleList = getBaseMapper().selectList(queryWrapper);
        if(CollectionUtils.isEmpty(sysCodeRuleList)) {
           return ""; 
        }
        // 按ruleSeqNo排序，取ruleSeqNo最大的记录
        if(sysCodeRuleList.size() > 1) {
            sysCodeRuleList.sort((arg0,arg1) -> {
                if(arg0.getRuleSeqNo() == arg1.getRuleSeqNo()){
                    return 0;
                }
                return arg0.getRuleSeqNo() < arg1.getRuleSeqNo() ? -1 : 1;
            });
        }
        SysCodeRule lastestSysCodeRule = sysCodeRuleList.get(0);
        
        String prefix = lastestSysCodeRule.getNoPrefix();
        Integer noLength = lastestSysCodeRule.getNoLength();
        Integer serialLength = noLength - prefix.length();
        Integer nextSerial = lastestSysCodeRule.getCurrSerial() + 1;
        Integer nextSerialLength = String.valueOf(nextSerial).length();
        
        if(nextSerial > lastestSysCodeRule.getMaxSerial()) {
            throw new BaseRuntimeException("-1", "当前编码超过最大范围限制");
        }else {
            StringBuilder sb = new StringBuilder(prefix);
            for(int i = 0; i < serialLength - nextSerialLength; i++) {
                sb.append('0');
            }
            sb.append(nextSerial);
            // 更新规则
            lastestSysCodeRule.setCurrSerial(nextSerial);
            lastestSysCodeRule.setUpdateTime(new Date());
            getBaseMapper().updateById(lastestSysCodeRule);
            
            return sb.toString(); 
        }
    }

}
