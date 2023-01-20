package plus.maa.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import plus.maa.backend.common.annotation.CurrentUser;
import plus.maa.backend.controller.request.CopilotCUDRequest;
import plus.maa.backend.controller.request.CopilotQueriesRequest;
import plus.maa.backend.controller.request.CopilotRatingReq;
import plus.maa.backend.controller.response.CopilotInfo;
import plus.maa.backend.controller.response.CopilotPageInfo;
import plus.maa.backend.controller.response.MaaResult;
import plus.maa.backend.controller.response.MaaResultException;
import plus.maa.backend.service.CopilotService;
import plus.maa.backend.service.model.LoginUser;

/**
 * @author LoMu
 * Date  2022-12-25 17:08
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/copilot")
public class CopilotController {
    private final CopilotService copilotService;

    @PostMapping("/upload")
    public MaaResult<String> uploadCopilot(@CurrentUser LoginUser loginUser,
                                           @RequestBody CopilotCUDRequest request) {
        if (ObjectUtils.isEmpty(loginUser)) throw new MaaResultException("请先登录账号");
        return copilotService.upload(loginUser, request.getContent());
    }

    @PostMapping("/delete")
    public MaaResult<Void> deleteCopilot(@CurrentUser LoginUser loginUser,
                                         @RequestBody CopilotCUDRequest request) {
        if (ObjectUtils.isEmpty(loginUser)) throw new MaaResultException("请先登录账号");
        return copilotService.delete(loginUser, request);
    }

    @GetMapping("/get/{id}")
    public MaaResult<CopilotInfo> getCopilotById(@CurrentUser LoginUser user, @PathVariable("id") String id) {
        return copilotService.getCopilotById(user, id);
    }


    @GetMapping("/query")
    public MaaResult<CopilotPageInfo> queriesCopilot(@CurrentUser LoginUser loginUser, CopilotQueriesRequest copilotQueriesRequest) {
        return copilotService.queriesCopilot(loginUser, copilotQueriesRequest);
    }

    @PostMapping("/update")
    public MaaResult<Void> updateCopilot(@CurrentUser LoginUser loginUser, @RequestBody CopilotCUDRequest copilotCUDRequest) {
        if (ObjectUtils.isEmpty(loginUser)) throw new MaaResultException("请先登录账号");
        return copilotService.update(loginUser, copilotCUDRequest);
    }

    @PostMapping("/rating")
    public MaaResult<String> ratesCopilotOperation(@CurrentUser LoginUser loginUser, @RequestBody CopilotRatingReq copilotRatingReq) {
        return copilotService.rates(loginUser, copilotRatingReq);
    }

}
