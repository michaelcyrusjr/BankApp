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

import SummaryCard from '../../components/SummaryCard'
import AccountRow from '../../components/AccountRow'
import TransactionRow from '../../components/TransactionRow'
import { useEffect, useState } from 'react'
import { getAccounts } from '../../api/accountsApi'

const Dashboard = () => {
  const [accounts, setAccounts] = useState([])
  const [loading, setLoading] = useState(true)
  console.log('render accounts:', accounts)

  useEffect(() => {
    const loadAccounts = async () => {
      try {
        const data = await getAccounts()
        console.log('fetched accounts:', data)
        setAccounts(data)
      } catch (error) {
        console.error('Failed to load accounts:', error)
      } finally {
        setLoading(false)
      }
    }

    loadAccounts()
  }, [])

  useEffect(() => {
    console.log('accounts changed:', accounts)
  }, [accounts])

  const transactions = [
    { name: 'Salary Deposit', date: 'May 24, 2026', amount: '+$3,200.00' },
    { name: 'Electric Bill', date: 'May 22, 2026', amount: '-120.00' },
    { name: 'Groceries', date: 'May 21, 2026', amount: '-$86.44' },
  ]

  const totalBalance = '$27,540.00'
  const totalAccounts = accounts.length
  const totalIncome = '$4,850'
  const totalExpenses = '$2,310.00'
  const recentActivity = '5'

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
          <SummaryCard title="Total Balance" value={totalBalance} />
        </CCol>

        <CCol md={2}>
          <SummaryCard title="Total Accounts" value={totalAccounts} />
        </CCol>

        <CCol md={2}>
          <SummaryCard title="Total Income" value={totalIncome} />
        </CCol>

        <CCol md={2}>
          <SummaryCard title="Total Expenses" value={totalExpenses} />
        </CCol>

        <CCol md={2}>
          <SummaryCard title="Recent Activity" value={recentActivity} />
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
                  <AccountRow
                    key={account.id}
                    accountNumber={account.accountNumber}
                    balance={account.balance}
                  />
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
                  <TransactionRow
                    key={transaction.name}
                    name={transaction.name}
                    date={transaction.date}
                    amount={transaction.amount}
                  />
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
