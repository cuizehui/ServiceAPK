package com.nela.common.constants;

public class JsonParamConstants {

    // define
    public static final int QRCARD_PCC_TYPE_VCARD = 0;
    public static final int QRCARD_PCC_TYPE_OBJ = 1;

    // action
    public static final String RCS_ACTION_CLI = "rcs_action_cli";
    public static final String RCS_ACTION_IM = "rcs_action_im";

    public static final String RCS_NOTIFY_PERMISSION = "com.juphoon.rcs.notify.permission";
    public static final String RCS_ACTION_CLI_NOTIFY = "rcs_action_cli_notify";
    public static final String RCS_ACTION_IM_NOTIFY = "rcs_action_im_notify";

    // intent key, value is json string
    public static final String RCS_JSON_KEY = "rcs_json_key";

    //json param

    // String
    public static final String RCS_JSON_ACTION = "rcs_json_action";

    /**** action enum ****/
    public static final String RCS_JSON_ACTION_CLI_CMCSS_TOKEN = "rcs_json_action_cli_cmcc_token";
    // login
    public static final String RCS_JSON_ACTION_CLI_REG_OK = "rcs_json_action_cli_reg_ok";
    public static final String RCS_JSON_ACTION_CLI_REG_FAILED = "rcs_json_action_cli_reg_failed";
    public static final String RCS_JSON_ACTION_CLI_SERV_LOGIN_OK = "rcs_json_action_cli_serv_login_ok";
    public static final String RCS_JSON_ACTION_CLI_SERV_LOGIN_FAILED = "rcs_json_action_cli_serv_login_failed";
    public static final String RCS_JSON_ACTION_CLI_SERV_LOGOUT = "rcs_json_action_cli_serv_logout";
    public static final String RCS_JSON_ACTION_CLI_STATE_CODE = "rcs_json_action_cli_state_code";
    public static final String RCS_JSON_ACTION_CLI_USE_RCS = "rcs_json_action_cli_use_rcs";
    public static final String RCS_JSON_ACTION_CLI_TRANSLATE_SMS = "rcs_json_action_cli_translate_sms";
    public static final String RCS_JSON_ACTION_CLI_CARD_CHANGE = "rcs_json_action_cli_card_change";
    public static final String RCS_JSON_ACTION_CLI_AKA_ERROR = "rcs_json_action_cli_aka_error";
    
    // gba
    public static final String RCS_JSON_ACTION_GBA_BTID_KSNAF = "rcs_json_action_gba_btid_ksnaf";
    // im
    public static final String RCS_JSON_ACTION_IM_MSG_RECV_MSG = "rcs_json_action_im_msg_recv_msg";
    public static final String RCS_JSON_ACTION_IM_MSG_RECV_SYS_MSG = "rcs_json_action_im_msg_recv_sys_msg";
    public static final String RCS_JSON_ACTION_IM_MSG_RECV_SMS_INFO = "rcs_json_action_im_msg_recv_sms_info";
    public static final String RCS_JSON_ACTION_IM_MSG_SEND_OK = "rcs_json_action_im_msg_send_ok";
    public static final String RCS_JSON_ACTION_IM_MSG_SEND_FAILED = "rcs_json_action_im_msg_send_failed";
    public static final String RCS_JSON_ACTION_GROUPCHAT_RECV_INVITE = "rcs_json_action_groupchat_recv_invite";
    public static final String RCS_JSON_ACTION_1To1_RECV_INVITE = "rcs_json_action_1to1_recv_invite";
    public static final String RCS_JSON_ACTION_GROUPCHAT_ACCEPTED = "rcs_json_action_groupchat_accepted";
    public static final String RCS_JSON_ACTION_GROUPCHAT_SIGNAL_ACCEPTED = "rcs_json_action_groupchat_signal_accepted";
    public static final String RCS_JSON_ACTION_GROUPCHAT_REJECTED = "rcs_json_action_groupchat_rejected";
    public static final String RCS_JSON_ACTION_GROUPCHAT_CANCELED = "rcs_json_action_groupchat_canceled";
    public static final String RCS_JSON_ACTION_GROUPCHAT_RELEASED = "rcs_json_action_groupchat_released";
    public static final String RCS_JSON_ACTION_GROUPCHAT_LEAVE_OK = "rcs_json_action_groupchat_leave_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_LEAVE_FAIL = "rcs_json_action_groupchat_leave_fail";
    public static final String RCS_JSON_ACTION_GROUPCHAT_DISSOLVE_OK = "rcs_json_action_groupchat_dissolve_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_DISSOLVE_FAIL = "rcs_json_action_groupchat_dissolve_fail";
    public static final String RCS_JSON_ACTION_GROUPCHAT_PARTP_ADD_OK = "rcs_json_action_groupchat_partp_add_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_PARTP_ADD_FAIL = "rcs_json_action_groupchat_partp_add_fail";
    public static final String RCS_JSON_ACTION_GROUPCHAT_PARTP_EPL_OK = "rcs_json_action_groupchat_partp_epl_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_PARTP_EPL_FAIL = "rcs_json_action_groupchat_partp_epl_fail";
    public static final String RCS_JSON_ACTION_GROUPCHAT_PARTP_UPDATE = "rcs_json_action_groupchat_partp_update";
    public static final String RCS_JSON_ACTION_GROUPCHAT_RECV_MSG = "rcs_json_action_groupchat_recv_msg";
    public static final String RCS_JSON_ACTION_GROUPCHAT_MSG_SEND_RESULT = "rcs_json_action_groupchat_msg_send_result";
    public static final String RCS_JSON_ACTION_CONFS_SUB_LIST = "rcs_json_action_confs_sub_list";
    public static final String RCS_JSON_ACTION_CONFS_SUB_INFO = "rcs_json_action_confs_sub_info";
    public static final String RCS_JSON_ACTION_IMDN_STATUS = "rcs_json_action_imdn_status";
    public static final String RCS_JSON_ACTION_FILE_RECV_INVITE = "rcs_json_action_file_recv_invite";
    public static final String RCS_JSON_ACTION_FILE_RECV_DONE = "rcs_json_action_file_recv_done";
    public static final String RCS_JSON_ACTION_FILE_SEND_FAILED = "rcs_json_action_file_send_failed";
    public static final String RCS_JSON_ACTION_FILE_RECV_FAILED = "rcs_json_action_file_recv_failed";
    public static final String RCS_JSON_ACTION_FILE_SENDING_PROGRESS = "rcs_json_action_file_sending_progress";
    public static final String RCS_JSON_ACTION_FILE_RECVING_PROGRESS = "rcs_json_action_file_recving_progress";
    public static final String RCS_JSON_ACTION_FILE_SEND_OK = "rcs_json_action_file_send_ok";
    public static final String RCS_JSON_ACTION_FILE_RESUME_RECV_INVT_FROM_SENDER = "rcs_json_action_file_resume_recv_invt_from_sender";
    public static final String RCS_JSON_ACTION_FILE_FETCH_REJECT = "rcs_json_action_file_fetch_reject";
    public static final String RCS_JSON_ACTION_GS_RECV_INVITE = "rcs_json_action_gs_recv_invite";
    public static final String RCS_JSON_ACTION_GS_SHARE_OK = "rcs_json_action_gs_share_ok";
    public static final String RCS_JSON_ACTION_GS_SHARE_FAILED = "rcs_json_action_gs_share_failed";
    public static final String RCS_JSON_ACTION_GS_RECV_DONE = "rcs_json_action_gs_recv_done";
    public static final String RCS_JSON_ACTION_GS_RECV_FAILED = "rcs_json_action_gs_recv_failed";
    public static final String RCS_JSON_ACTION_GROUPCHAT_SUBJECT_MDFY_OK = "rcs_json_action_groupchat_subject_mdfy_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_SUBJECT_MDFY_FAILED = "rcs_json_action_groupchat_subject_mdfy_failed";
    public static final String RCS_JSON_ACTION_GROUPCHAT_SUBJECT_CHGED = "rcs_json_action_groupchat_subject_chged";
    public static final String RCS_JSON_ACTION_GROUPCHAT_CHAIRMAN_MDFY_OK = "rcs_json_action_groupchat_chairman_mdfy_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_CHAIRMAN_MDFY_FAILED = "rcs_json_action_groupchat_chairman_mdfy_failed";
    public static final String RCS_JSON_ACTION_GROUPCHAT_CHAIRMAN_REQUEST = "rcs_json_action_groupchat_chairman_request";
    public static final String RCS_JSON_ACTION_GROUPCHAT_DISPLAYNAME_MDFY_OK = "rcs_json_action_groupchat_displayname_mdfy_ok";
    public static final String RCS_JSON_ACTION_GROUPCHAT_DISPLAYNAME_MDFY_FAILED = "rcs_json_action_groupchat_displayname_mdfy_failed";
    public static final String RCS_JSON_ACTION_OFFLINE_MSG_START = "rcs_json_action_offline_msg_start";
    public static final String RCS_JSON_ACTION_OFFLINE_MSG_STOP = "rcs_json_action_offline_msg_stop";
    public static final String RCS_JSON_ACTION_TRANSID_IMDN_UPDATE = "rcs_json_action_imdn_transid_update";
    public static final String RCS_JSON_ACTION_HTTP_THUMB_DOWNLOAD_RESULT = "rcs_json_action_http_thumb_download_result";

    //chatbot
    public static final String RCS_JSON_ACTION_CHATBOT_LIST_OK = "rcs_json_action_chatbot_list_ok";
    public static final String RCS_JSON_ACTION_CHATBOT_LIST_NO_UPDATE = "rcs_json_action_chatbot_no_update";
    public static final String RCS_JSON_ACTION_CHATBOT_LIST_FAIL = "rcs_json_action_chatbot_list_fail";
    public static final String RCS_JSON_ACTION_CHATBOT_INFO_OK = "rcs_json_action_chatbot_info_ok";
    public static final String RCS_JSON_ACTION_CHATBOT_INFO_FAIL = "rcs_json_action_chatbot_info_fail";
    public static final String RCS_JSON_ACTION_CHATBOT_INFO_NO_UPDATE = "rcs_json_action_chatbot_info_no_update";

    // String
    public static final String RCS_JSON_FIRST_LOGIN = "first_login";
    // boolean
    public static final String RCS_JSON_USE_RCS = "use_rcs";
    // boolean
    public static final String RCS_JSON_TRANSLATE_SMS = "translate_sms";
    // String
    public static final String RCS_JSON_ACCOUNT = "account";
    // int
    public static final String RCS_JSON_SUB_ID = "sub_id";
    // String
    public static final String RCS_JSON_USER = "user";
    // boolean
    public static final String RCS_JSON_RESULT = "result";
    // String
    public static final String RCS_JSON_COOKIE = "cookie";
    // String
    public static final String RCS_JSON_TOKEN = "token";
    // int
    public static final String RCS_JSON_CONTENT_TYPE = "content_type";
    // String
    public static final String RCS_JSON_CONV_ID = "conv_id";
    // String
    public static final String RCS_JSON_CONT_ID = "cont_id";
    // String
    public static final String RCS_JSON_IMDN_ID = "imdn_id";
    // int
    public static final String RCS_JSON_IMDN_TYPE = "imdn_type";
    // String
    public static final String RCS_JSON_NAME = "name";
    // String
    public static final String RCS_JSON_ADDRESS = "address";
    // String
    public static final String RCS_JSON_BODY = "body";
    // long
    public static final String RCS_JSON_TIMESTAMP = "timestamp";
    // boolean
    public static final String RCS_JSON_BURN_AFTER_READ = "burn_after_read";
    // boolean
    public static final String RCS_JSON_PUBLIC = "public";
    // boolean
    public static final String RCS_JSON_EMOTICON = "emoticon";
    // boolean
    public static final String RCS_JSON_CLOUD_FILE = "cloud_file";
    // boolean
    public static final String RCS_JSON_CARD = "card";
    // boolean
    public static final String RCS_JSON_REDBAG = "redbag";
    // boolean
    public static final String RCS_JSON_A2P = "a2p";
    // jsonobj
    public static final String RCS_JSON_IMDN_STATUS = "imdn_status";
    // jsonarray
    public static final String RCS_JSON_GROUPS = "groups";
    // int
    public static final String RCS_JSON_CONF_ID = "conf_id";
    // int
    public static final String RCS_JSON_ERROR_CODE = "error_code";
    // boolean
    public static final String RCS_JSON_MESSAGE_DIRECT = "message_direct";
    // boolean
    public static final String RCS_JSON_MESSAGE_CC = "message_cc";
    // boolean
    public static final String RCS_JSON_MESSAGE_SILENCE = "message_silence";
    // boolean
    public static final String RCS_JSON_MESSAGE_AT = "message_at";
    // boolean
    public static final String RCS_JSON_MESSAGE_REPORT = "message_report";
    // boolean
    public static final String RCS_JSON_MESSAGE_OFFLINE = "message_offline";
    // String
    public static final String RCS_JSON_GROUP_NAME = "group_name";
    // int
    public static final String RCS_JSON_GROUP_TYPE = "group_type";
    // String
    public static final String RCS_JSON_GROUP_CHAT_ID = "group_chat_id";
    // String
    public static final String RCS_JSON_GROUP_SESS_IDENTIFY = "group_sess_identify";
    // String
    public static final String RCS_JSON_GROUP_ORGANZIER_PHONE = "group_organizer_phone";
    // jsonarray
    public static final String RCS_JSON_GROUP_MEMBERS = "group_members";
    // jsonobj
    public static final String RCS_JSON_GROUP_MEMBER_STATUS = "group_member_status";
    // jsonobj
    public static final String RCS_JSON_GROUP_MEMBER_DISPLAY_NAME = "group_member_display_name";
    // jsonobj
    public static final String RCS_JSON_GROUP_MEMBER_ETYPE = "group_member_etype";
    // String
    public static final String RCS_JSON_GROUP_CHAIRMAN = "group_chairman";
    // jsonobj
    public static final String RCS_JSON_GROUP_DISPLAY_NAME = "group_display_name";
    // int
    public static final String RCS_JSON_GROUP_STATE = "group_sess_state";
    // int
    public static final String RCS_JSON_GROUP_INVITE_OFFLINE = "group_sess_invite_offline";
    // int
    public static final String RCS_JSON_GROUP_INVITE_CREATE = "group_sess_invite_create";
    // boolean
    public static final String RCS_JSON_GROUP_PARTPUPDATE_FULL = "group_partpupadte_full";
    // string
    public static final String RCS_JSON_FILE_NAME = "file_name";
    // String
    public static final String RCS_JSON_FILE_TYPE = "file_type";
    // String
    public static final String RCS_JSON_FILE_PATH = "file_path";
    // String
    public static final String RCS_JSON_THUMB_PATH = "thumb_path";
    // String
    public static final String RCS_JSON_TRANS_ID = "trans_id";
    // int
    public static final String RCS_JSON_FILE_SIZE = "file_size";
    // int
    public static final String RCS_JSON_FILE_TRANS_SIZE = "trans_size";
    // int
    public static final String RCS_JSON_FILE_DURATION = "file_duration";
    // int
    public static final String RCS_JSON_SESS_ID = "sess_id";
    // int
    public static final String RCS_JSON_OBJ_ID = "obj_id";
    // string
    public static final String RCS_JSON_PHONE = "phone";
    // string
    public static final String RCS_JSON_CAP = "cap";
    // boolean
    public static final String RCS_JSON_ONLINE = "online";
    // string
    public static final String RCS_JSON_PA_XML = "pa_xml";
    // string
    public static final String RCS_JSON_PA_UUID = "pa_uuid";
    // boolean
    public static final String RCS_JSON_IS_BLOCKED = "is_blocked";

    /**** json sub param ****/
    // String
    public static final String RCS_JSON_GS_LATITUDE = "gs_latitude";
    // String
    public static final String RCS_JSON_GS_LONGTUDE = "gs_longtude";
    // String
    public static final String RCS_JSON_GS_RADIUS = "gs_radius";
    // String
    public static final String RCS_JSON_GS_LOCATION_NAME = "gs_location_name";
    // String
    public static final String RCS_JSON_GBA_BTID = "gba_btid";
    // String
    public static final String RCS_JSON_GBA_KSNAF = "gba_ksnaf";
    // String
    public static final String RCS_JSON_GBA_NAFID = "gba_nafid";

    // String
    public static final String RCS_JSON_HTTP_XML = "http_xml";
    // boolean
    public static final String RCS_JSON_CHATBOT = "chatbot";
    // String
    public static final String RCS_JSON_CHATBOT_SERVICE_ID = "chatbot_service_id";
    // boolean
    public static final String RCS_JSON_CHATBOT_IS_BLACK = "chatbot_is_black";
    // boolean
    public static final String RCS_JSON_CHATBOT_IS_RECOMMEND_LIST = "chatbot_is_recommend_list";
    // boolean
    public static final String RCS_JSON_CHATBOT_IS_SPECIFIC_LIST = "chatbot_is_specific_list";

    public static final String RCS_JSON_CHATBOT_ETAG = "chatbot_etag";
    public static final String RCS_JSON_CHATBOT_ACTION = "chatbot_action";
    // String
    public static final String RCS_JSON_CHATBOT_CARD = "chatbot_card";
    // String
    public static final String RCS_JSON_CHATBOT_SUGGESTION = "chatbot_suggestion";
    // String
    public static final String RCS_JSON_CHATBOT_A2P = "chatbot_a2p";

    // bool
    public static final String RCS_JSON_IS_HTTP = "is_http";

    public static final String RCS_JSON_ACTION_CAP_OK = "rcs_json_action_cap_ok";

    public static final String RCS_JSON_CAP_UPDATE_TIME = "cap_update_time";

    // String
    public static final String RCS_JSON_HTTP_MSG_XML = "http_msg_xml";

    public static final String RCS_JSON_RCS = "isRcs";

//    public static final String RCS_JSON_ACTION_CAP_OK = "rcs_json_action_cap_ok";
    public static final String RCS_JSON_ACTION_CAP_FAILED = "rcs_json_action_cap_failed";
    public static final String RCS_JSON_ACTION_CAP_UPDATE = "rcs_json_action_cap_update";

    // int
    public static final String RCS_JSON_CACHE_SECONDS = "cache_seconds";

    // string
    public static final String RCS_JSON_TRAFFIC_TYPE = "traffic_type";
    // boolean
    public static final String RCS_JSON_HAS_GET_DETAIL = "has_get_detail";
    // int 群订阅版本号
    public static final String RCS_JSON_CONF_SUBS_VER = "conf_subs_ver";
}
