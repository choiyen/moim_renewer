package moim.renew.backend.domain.Moim.MoimApproval.Handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * MyBatis TypeHandler 구현체
 *
 * - DB의 TEXT 또는 VARCHAR 칼럼에 String[] 데이터를 JSON 문자열로 변환하여 저장
 * - DB에서 읽을 때는 JSON 문자열을 다시 String[]로 변환
 * - 특정 칼럼 매핑 시, MyBatis 매퍼 XML에서 typeHandler 속성으로 이 클래스를 지정해야 함
 */
@MappedTypes(String[].class)
public class StringArrayJsonTypeHandler extends BaseTypeHandler<String[]> {

    // JSON 직렬화/역직렬화를 위해 ObjectMapper 사용
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * DB에 데이터를 저장할 때 호출
     * String[] → JSON 문자열로 변환 후 PreparedStatement에 바인딩
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter)); // 배열 → JSON
        } catch (JsonProcessingException e) {
            throw new SQLException("JSON 직렬화 실패", e);
        }
    }

    /**
     * ResultSet에서 컬럼명을 이용해 값 조회
     * JSON 문자열 → String[] 변환
     */
    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        try {
            return json == null ? null : objectMapper.readValue(json, String[].class);
        } catch (Exception e) {
            throw new SQLException("JSON 역직렬화 실패", e);
        }
    }

    /**
     * ResultSet에서 컬럼 인덱스를 이용해 값 조회
     * JSON 문자열 → String[] 변환
     */
    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        try {
            return json == null ? null : objectMapper.readValue(json, String[].class);
        } catch (Exception e) {
            throw new SQLException("JSON 역직렬화 실패", e);
        }
    }

    /**
     * CallableStatement(프로시저 호출)에서 컬럼 인덱스를 이용해 값 조회
     * JSON 문자열 → String[] 변환
     */
    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        try {
            return json == null ? null : objectMapper.readValue(json, String[].class);
        } catch (Exception e) {
            throw new SQLException("JSON 역직렬화 실패", e);
        }
    }
}
