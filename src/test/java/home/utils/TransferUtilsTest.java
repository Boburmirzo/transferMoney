package home.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import home.model.Account;

public class TransferUtilsTest {

	private static final long BALANCE = 10000;
	private static final long AMOUNT = 1000;
	private TransferUtils transferUtils = new TransferUtils();

	@Test
	public void testTransfer() throws Exception {
		Account source = new Account("1", BALANCE);
		Account destination = new Account("2", BALANCE);

		transferUtils.transferMoney(source, destination, AMOUNT);

		Assert.assertThat(source.getBalance(), Is.is(BALANCE - AMOUNT));
		Assert.assertThat(destination.getBalance(), Is.is(BALANCE + AMOUNT));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransferToTheSameAccount() {
		Account account = new Account("1", BALANCE);

		transferUtils.transferMoney(account, account, AMOUNT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransferAmountGreaterThanBalance() {
		Account source = new Account("1", BALANCE);
		Account destination = new Account("2", BALANCE);

		transferUtils.transferMoney(source, destination, BALANCE * 2);
	}
}