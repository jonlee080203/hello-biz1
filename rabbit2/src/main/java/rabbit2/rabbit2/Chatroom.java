package rabbit2.rabbit2;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownSignalException;

public class Chatroom {
	private static Channel channel;
	private static Connection connection;
	private static ConnectionFactory factory;
	private static String queueName;
	private static String name;
	private static Scanner scanner;

	public static void main(String[] args) throws IOException,
			ShutdownSignalException, ConsumerCancelledException,
			InterruptedException, TimeoutException {
		init();// 初始化

		if (StringUtils.isEmpty(name)) {// 输入名字为空
			System.out.println("Your nickname is empty");
			System.exit(0);
		} else {
			System.out.println("Hello " + name
					+ ",you can chat from now, enjoy it!");
			while (true) {
				String said = scanner.nextLine();
				String message1 = name + " said " + said;
				if (said.equals("q")) {
					System.out.println("bye");
					System.out.println("Process finished with eixt code 0!");
					System.exit(0);
				} else if (StringUtils.isEmpty(said)) {
					// 发送内容为空就什么也不做
				} else {
					channel.basicPublish("chatroom", "", null,
							message1.getBytes());

					Consumer consumer = new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag,
								Envelope envelope,
								AMQP.BasicProperties properties, byte[] body)
								throws IOException {
							String message = new String(body, "UTF-8");
							System.out.println(message);
						}
					};
					channel.basicConsume(queueName, false, consumer);
				}
			}
		}
	}

	// 初始化
	public static void init() throws IOException {
		// 创建一个连接接收数据
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();

		queueName = "fanoutQueue";

		// 为Channel定义queue的属性，queueName为queue名称 ，第二个属性设置queue是否持久化
		channel.queueDeclare(queueName, false, false, true, null);
		channel.exchangeDeclare("chatroom", "fanout");
		channel.queueBind(queueName, "chatroom", "");

		scanner = new Scanner(System.in);
		System.out.println("Welcome to Chatroom");
		System.out.println("Type q to exit..");
		System.out.println("Input your nickname:");
		name = scanner.nextLine();
	}

}
