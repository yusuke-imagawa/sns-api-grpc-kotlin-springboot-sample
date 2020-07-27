package com.talking.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

/**
 * 実行されている環境に関するクラスです
 * Created by imagawa on 2016/07/13.
 */
@Service
class EnvironmentService {

    companion object {
        const val ACTIVE_PROFILE_DEV = "development"
        const val ACTIVE_PROFILE_PRODUCTION = "production"
    }

    @Autowired
    private lateinit var env: Environment

    /**
     * 本番環境であるか判定します
     * @return
     */
    val isProduction: Boolean
        get() {
            val activeProfiles = env.activeProfiles
            for (activeProfile in activeProfiles) {
                if (activeProfile.contains(ACTIVE_PROFILE_PRODUCTION)) {
                    return true
                }
            }
            return false
        }

    /**
     * 開発環境であるか判定します
     * @return
     */
    val isDevelop: Boolean
        get() = !isProduction

}