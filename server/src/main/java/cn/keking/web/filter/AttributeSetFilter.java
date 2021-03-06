package cn.keking.web.filter;

import cn.keking.config.ConfigConstants;
import cn.keking.config.WatermarkConfigConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chenjh
 * @since 2020/5/13 18:34
 */
public class AttributeSetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        this.setWatermarkAttribute(request);
        this.setFileAttribute(request);
        filterChain.doFilter(request, response);
    }

    /**
     * 设置办公文具预览逻辑需要的属性
     * @param request request
     */
    private void setFileAttribute(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        request.setAttribute("pdfDownloadDisable", ConfigConstants.getPdfDownloadDisable());
        request.setAttribute("pdfXianzhi", ConfigConstants.getpdfXianzhi());
        request.setAttribute("mediagg", ConfigConstants.getmediagg());
        request.setAttribute("fileKey", httpRequest.getParameter("fileKey"));
        request.setAttribute("switchDisabled", ConfigConstants.getOfficePreviewSwitchDisabled());
        request.setAttribute("fileUploadDisable", ConfigConstants.getFileUploadDisable());
    }

    /**
     * 设置水印属性
     * @param request request
     */

    private void setWatermarkAttribute(ServletRequest request) {
        String watermarkTxt = request.getParameter("watermarkTxt");
        if(watermarkTxt == null || "".equals(watermarkTxt)){
        }else {
            watermarkTxt= HtmlUtils.htmlEscape(watermarkTxt);
        }

        if( WatermarkConfigConstants.getWatermarkqy().equalsIgnoreCase("true")){
            request.setAttribute("watermarkTxt", watermarkTxt != null ? watermarkTxt : WatermarkConfigConstants.getWatermarkTxt());
        }else {
            request.setAttribute("watermarkTxt",  WatermarkConfigConstants.getWatermarkTxt());
        }
        request.setAttribute("watermarkXSpace", WatermarkConfigConstants.getWatermarkXSpace());
        request.setAttribute("watermarkYSpace", WatermarkConfigConstants.getWatermarkYSpace());
        request.setAttribute("watermarkFont", WatermarkConfigConstants.getWatermarkFont());
        request.setAttribute("watermarkFontsize", WatermarkConfigConstants.getWatermarkFontsize());
        request.setAttribute("watermarkColor", WatermarkConfigConstants.getWatermarkColor());
        request.setAttribute("watermarkAlpha", WatermarkConfigConstants.getWatermarkAlpha());
        request.setAttribute("watermarkWidth", WatermarkConfigConstants.getWatermarkWidth());
        request.setAttribute("watermarkHeight", WatermarkConfigConstants.getWatermarkHeight());
        request.setAttribute("watermarkAngle", WatermarkConfigConstants.getWatermarkAngle());
    }

    @Override
    public void destroy() {

    }
}
