import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CListGroup,
  CListGroupItem,
  CRow,
} from '@coreui/react'

const Dashboard = () => {
  const accounts = [
    { name: 'Checking Account', number: '**** 1234', balance: '$12,540.00' },
    { name: 'Savings Account', number: '**** 5678', balance: '$10, 250.00' },
    { name: 'Credit Card', number: '**** 9012', balance: '-1,250.00' },
  ]

  const transactions = [
    { name: 'Salary Deposit', date: 'May 24, 2026', amount: '+$3,200.00' },
    { name: 'Electric Bill', date: 'May 22, 2026', amount: '-120.00' },
    { name: 'Groceries', date: 'May 21, 2026', amount: '-$86.44' },
  ]

  return (
    <>
      <CRow className="mb-4">
        <CCol xs={12}>
          <h1 className="mb-1">Welcome back, Michael!</h1>
          <p className="text-medium-emphasis mb-0">Here is an overview of your accounts today.</p>
        </CCol>
      </CRow>

      <CRow className="g-4 mb-4">
        <CCol md={2}>
          <CCard>
            <CCardBody>
              <div className="text-medium-emphasis">Total Balance</div>
              <div className="fs-3 fw-semibold">$27,540.00</div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol md={2}>
          <CCard>
            <CCardBody>
              <div className="text-medium-emphasis">Accounts</div>
              <div className="fs-3 fw-semibold">3</div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol md={2}>
          <CCard>
            <CCardBody>
              <div className="text-medium-emphasis">Total Income</div>
              <div className="fs-3 fw-semibold">$4,850</div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol md={2}>
          <CCard>
            <CCardBody>
              <div className="text-medium-emphasis">Tatal Expenses</div>
              <div className="fs-3 fw-semibold">$2,310</div>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol md={2}>
          <CCard>
            <CCardBody>
              <div className="text-medium-emphasis">Recent Activity</div>
              <div className="fs-3 fw-semibold">5</div>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>

      <CRow className="g-4">
        <CCol lg={7}>
          <CCard>
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Accounts Overview</strong>
              <CButton color="primary" size="sm">
                Transfer Money
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CListGroup flush>
                {accounts.map((account) => (
                  <CListGroupItem
                    key={account.number}
                    className="d-flex justify-content-between align-items-center py-3"
                  >
                    <div>
                      <div className="fw-semibold">{account.name}</div>
                      <div className="text-medium-emphasis small">{account.number}</div>
                    </div>
                    <div className="fw-semibold">{account.balance}</div>
                  </CListGroupItem>
                ))}
              </CListGroup>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol lg={5}>
          <CCard>
            <CCardHeader className="d-flex justify-content-between align-items-center">
              <strong>Recent Transactions</strong>
              <CButton color="link" size="sm" className="text-decoration-none p-0">
                View All
              </CButton>
            </CCardHeader>
            <CCardBody>
              <CListGroup flush>
                {transactions.map((transaction) => (
                  <CListGroupItem key={transaction.name} className="px-0 py-3">
                    <div className="d-flex justify-content-between">
                      <div>
                        <div className="fw-semibold">{transaction.name}</div>
                        <div className="text-medium-emphasis small">{transactions.date}</div>
                      </div>
                      <div className="fw-semibold">{transaction.amount}</div>
                    </div>
                  </CListGroupItem>
                ))}
              </CListGroup>
            </CCardBody>
          </CCard>
        </CCol>

        <CCol xs={12}>
          <CCard className="mt-4">
            <CCardHeader>
              <strong>Quick Actions</strong>
            </CCardHeader>
            <CCardBody className="d-flex gap-2 flex-wrap">
              <CButton color="primary">Transfer Money</CButton>
              <CButton color="secondary">Deposit</CButton>
              <CButton color="secondary">Withdraw</CButton>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </>
  )
}

export default Dashboard
