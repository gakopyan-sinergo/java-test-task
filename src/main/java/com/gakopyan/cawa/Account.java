package com.gakopyan.cawa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @Getter @Setter private Long id;

    @Column(name = "account_details")
    @Getter @Setter private String accountDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;
        return accountDetails != null ? accountDetails.equals(account.accountDetails) : account.accountDetails == null;
    }

    public static Account fromJsonString(String source) throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(source);
        Account account = new Account(Long.parseLong(jsonObject.get("id").toString()),
                jsonObject.get("accountDetails").toString());
        return account;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (accountDetails != null ? accountDetails.hashCode() : 0);
        return result;
    }
}
