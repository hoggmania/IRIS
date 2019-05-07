package RestbucksModel;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rb_payment")
@SuppressWarnings("unused")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer Id;

	private String authorisationCode;
	private String orderId;
	
	@JoinColumn(name = "orderId", referencedColumnName = "Id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Order order;

	public Payment() {
	}
}